import { ref, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { CourseControllerService, TutorialControllerService } from '@/api'
import type { CourseDetailVO, TutorialBriefVO, TutorialDetailVO, CourseVO } from '@/api'

export type ActiveTab = 'TEXT' | 'VIDEO'
export type ActiveView = 'overview' | 'tutorial'

export function useCourseDetail() {
  const route = useRoute()
  const router = useRouter()

  // 状态
  const course = ref<CourseDetailVO | null>(null)
  const tutorialTree = ref<TutorialBriefVO[]>([])
  const recommendedCourses = ref<CourseVO[]>([])
  const activeTutorial = ref<TutorialBriefVO | null>(null)
  const tutorialContent = ref<TutorialDetailVO | null>(null)
  const activeTab = ref<ActiveTab>('TEXT')
  const activeView = ref<ActiveView>('overview')
  const loading = ref(true)
  const contentLoading = ref(false)

  // 计算属性
  const courseId = computed(() => route.params.id as string)

  // 过滤后的目录树（按 Tab 类型过滤）
  const filteredTree = computed(() => {
    if (activeTab.value === 'ALL') return tutorialTree.value

    return filterTreeByType(tutorialTree.value, activeTab.value)
  })

  // 过滤树函数：只保留包含匹配类型子节点的章节
  function filterTreeByType(tree: TutorialBriefVO[], type: 'TEXT' | 'VIDEO'): TutorialBriefVO[] {
    return tree
      .map(node => {
        // 如果是章节（有 children）
        if (node.children && node.children.length > 0) {
          const filteredChildren = filterTreeByType(node.children, type)
          if (filteredChildren.length > 0) {
            return { ...node, children: filteredChildren }
          }
          return null
        }
        // 如果是叶子节点，匹配类型则保留
        if (node.type === type) {
          return node
        }
        return null
      })
      .filter((node): node is TutorialBriefVO => node !== null)
  }

  // 找到第一个叶子节点（教程）
  function findFirstLesson(tree: TutorialBriefVO[]): TutorialBriefVO | null {
    for (const node of tree) {
      if (node.children && node.children.length > 0) {
        const found = findFirstLesson(node.children)
        if (found) return found
      } else {
        return node
      }
    }
    return null
  }

  // 加载课程详情
  async function loadCourse() {
    try {
      const res = await CourseControllerService.courseDetail(courseId.value)
      course.value = res.data || null
    } catch (error) {
      console.error('加载课程详情失败:', error)
    }
  }

  // 加载教程目录树
  async function loadTutorialTree() {
    try {
      const res = await TutorialControllerService.getTutorialBrief(courseId.value)
      tutorialTree.value = res.data || []
    } catch (error) {
      console.error('加载教程目录失败:', error)
    }
  }

  // 加载推荐课程
  async function loadRecommendedCourses() {
    try {
      const res = await CourseControllerService.pageQuery(
        undefined,
        undefined,
        undefined,
        1,
        6
      )
      recommendedCourses.value = res.data?.data || []
    } catch (error) {
      console.error('加载推荐课程失败:', error)
    }
  }

  // 加载教程内容
  async function loadTutorialContent(tutorialId: string) {
    contentLoading.value = true
    try {
      const res = await TutorialControllerService.getTutorialDetail(tutorialId)
      tutorialContent.value = res.data || null
    } catch (error) {
      console.error('加载教程内容失败:', error)
    } finally {
      contentLoading.value = false
    }
  }

  // 选中教程
  function selectTutorial(tutorial: TutorialBriefVO) {
    activeTutorial.value = tutorial
    activeView.value = 'tutorial'
    if (tutorial.id) {
      loadTutorialContent(tutorial.id)
    }
  }

  // 切换 Tab
  function handleTabChange(tab: ActiveTab) {
    activeTab.value = tab
  }

  // 开始学习
  function startLearning() {
    const firstLesson = findFirstLesson(tutorialTree.value)
    if (firstLesson) {
      selectTutorial(firstLesson)
    }
  }

  // 返回概览
  function backToOverview() {
    activeView.value = 'overview'
    activeTutorial.value = null
    tutorialContent.value = null
  }

  // 初始化加载
  async function initialize() {
    loading.value = true
    await Promise.all([
      loadCourse(),
      loadTutorialTree(),
      loadRecommendedCourses()
    ])
    loading.value = false
  }

  // 监听路由参数变化
  watch(courseId, (newId) => {
    if (newId) {
      activeView.value = 'overview'
      activeTutorial.value = null
      tutorialContent.value = null
      initialize()
    }
  })

  // 挂载时初始化
  onMounted(() => {
    initialize()
  })

  return {
    // 状态
    course,
    tutorialTree,
    filteredTree,
    recommendedCourses,
    activeTutorial,
    tutorialContent,
    activeTab,
    activeView,
    loading,
    contentLoading,

    // 方法
    selectTutorial,
    handleTabChange,
    startLearning,
    backToOverview
  }
}
