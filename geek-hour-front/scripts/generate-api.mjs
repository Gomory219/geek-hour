#!/usr/bin/env node

import { writeFileSync, readFileSync, rmSync, mkdirSync, existsSync } from 'fs'
import { execSync } from 'child_process'
import { fileURLToPath } from 'url'
import { dirname, join } from 'path'

const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)
const rootDir = join(__dirname, '..')

// 网关配置
const GATEWAY_URL = process.env.GATEWAY_URL || 'http://localhost:17000'
const API_BASE_URL = `${GATEWAY_URL}/api`
const SWAGGER_CONFIG_URL = `${GATEWAY_URL}/api/v3/api-docs/swagger-config`

// 临时文件目录
const TEMP_DIR = join(rootDir, 'temp-openapi')
const OUTPUT_DIR = join(rootDir, 'src', 'api', 'generated')

console.log(`📡 正在从网关拉取API文档: ${GATEWAY_URL}`)

try {
  // 创建临时目录
  if (!existsSync(TEMP_DIR)) {
    mkdirSync(TEMP_DIR, { recursive: true })
  }

  // 1. 拉取swagger配置，获取所有服务列表
  console.log(`🔍 获取服务列表...`)
  const swaggerConfigResp = await fetch(SWAGGER_CONFIG_URL)
  if (!swaggerConfigResp.ok) {
    throw new Error(`无法获取swagger配置: ${swaggerConfigResp.status}`)
  }
  const swaggerConfig = await swaggerConfigResp.json()

  console.log(`✅ 发现 ${swaggerConfig.urls.length} 个微服务:`)
  swaggerConfig.urls.forEach(service => {
    console.log(`   - ${service.name} (${service.serviceName})`)
  })

  // 2. 遍历每个服务，拉取OpenAPI文档并生成代码
  for (const service of swaggerConfig.urls) {
    const serviceName = service.name
    const openapiUrl = `${GATEWAY_URL}${service.url}`

    console.log(`\n📝 正在处理 ${serviceName}...`)
    console.log(`   拉取文档: ${openapiUrl}`)

    // 拉取OpenAPI文档
    const openapiResp = await fetch(openapiUrl)
    if (!openapiResp.ok) {
      console.error(`   ❌ 无法获取${serviceName}的文档，跳过`)
      continue
    }
    const openapiDoc = await openapiResp.json()

    // 重写servers URL，注入网关contextPath
    // 例如 gkh-user 的 contextPath 为 /api/user
    // 生成的 OpenAPI.BASE 将指向网关路由地址
    const contextPath = service.contextPath || ''
    openapiDoc.servers = [{
      url: contextPath,
      description: 'Gateway',
    }]

    // 写入临时文件
    const tempFile = join(TEMP_DIR, `${serviceName}.json`)
    writeFileSync(tempFile, JSON.stringify(openapiDoc, null, 2))

    // 生成TypeScript代码
    const serviceOutputDir = join(OUTPUT_DIR, serviceName)
    console.log(`   生成代码到: src/api/generated/${serviceName}/`)

    execSync(
      `npx openapi-typescript-codegen --input ${tempFile} --output ${serviceOutputDir} --client axios`,
      { cwd: rootDir, stdio: 'inherit' }
    )

    console.log(`   ✅ ${serviceName} 生成完成`)
  }

  // 3. 清理临时文件
  console.log(`\n🧹 清理临时文件...`)
  rmSync(TEMP_DIR, { recursive: true, force: true })

  console.log(`\n✨ API代码生成完成！`)
  console.log(`📂 生成目录: src/api/generated/`)

} catch (error) {
  console.error(`\n❌ 生成失败:`, error.message)
  process.exit(1)
}
