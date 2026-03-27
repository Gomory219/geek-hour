// GeekHour Prototype - Main JavaScript

// Search function
function handleSearch() {
    const searchInput = document.getElementById('searchInput') || document.querySelector('.search-input-large');
    const query = searchInput ? searchInput.value.trim() : '';

    if (query) {
        window.location.href = './search/index.html?q=' + encodeURIComponent(query);
    }
}

// Handle Enter key on search input
document.addEventListener('DOMContentLoaded', function() {
    // Search inputs
    const searchInputs = document.querySelectorAll('.search-input, .search-input-large');
    searchInputs.forEach(input => {
        input.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                handleSearch();
            }
        });
    });

    // Tab switching
    const tabs = document.querySelectorAll('.post-tab, .filter-tab, .content-tab, .player-tab');
    tabs.forEach(tab => {
        tab.addEventListener('click', function() {
            const tabGroup = this.parentElement;
            const isActive = this.classList.contains('active');

            // Remove active from all tabs in group
            tabGroup.querySelectorAll('.post-tab, .filter-tab, .content-tab, .player-tab').forEach(t => {
                t.classList.remove('active');
            });

            // Add active to clicked tab
            if (!isActive) {
                this.classList.add('active');
            } else {
                this.classList.add('active');
            }
        });
    });

    // Dropdown toggle
    const dropdowns = document.querySelectorAll('.dropdown');
    dropdowns.forEach(dropdown => {
        const trigger = dropdown.querySelector('button, .user-dropdown-btn');
        const menu = dropdown.querySelector('.dropdown-menu');

        if (trigger && menu) {
            trigger.addEventListener('click', function(e) {
                e.stopPropagation();
                menu.classList.toggle('show');
            });

            dropdown.addEventListener('mouseleave', function() {
                menu.classList.remove('show');
            });
        }
    });

    // Close dropdowns when clicking outside
    document.addEventListener('click', function() {
        document.querySelectorAll('.dropdown-menu.show').forEach(menu => {
            menu.classList.remove('show');
        });
    });

    // User menu item click
    const userMenuItems = document.querySelectorAll('.user-menu-item');
    userMenuItems.forEach(item => {
        item.addEventListener('click', function(e) {
            // Remove active from all items
            userMenuItems.forEach(i => i.classList.remove('active'));
            // Add active to clicked item
            this.classList.add('active');
        });
    });

    // Tab panels switching
    const contentTabs = document.querySelectorAll('.content-tabs .content-tab');
    contentTabs.forEach((tab, index) => {
        tab.addEventListener('click', function() {
            // Update active tab
            contentTabs.forEach(t => t.classList.remove('active'));
            this.classList.add('active');

            // Show corresponding panel
            const panels = this.closest('.course-main')?.querySelectorAll('.tab-panel');
            if (panels) {
                panels.forEach(panel => panel.classList.remove('active'));
                if (panels[index]) {
                    panels[index].classList.add('active');
                }
            }
        });
    });

    // Player tab panels
    const playerTabs = document.querySelectorAll('.player-tabs .player-tab');
    playerTabs.forEach((tab, index) => {
        tab.addEventListener('click', function() {
            // Update active tab
            playerTabs.forEach(t => t.classList.remove('active'));
            this.classList.add('active');

            // Show corresponding panel
            const panels = this.closest('.player-main')?.querySelectorAll('.player-content .tab-panel');
            if (panels) {
                panels.forEach(panel => panel.classList.add('hidden'));
                panel.classList.remove('active');
                if (panels[index]) {
                    panels[index].classList.remove('hidden');
                    panels[index].classList.add('active');
                }
            }
        });
    });

    // Tag remove buttons
    const tagRemoveBtns = document.querySelectorAll('.tag-remove');
    tagRemoveBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            this.closest('.tag-input-item')?.remove();
        });
    });

    // Toggle switches
    const toggleSwitches = document.querySelectorAll('.toggle-switch input');
    toggleSwitches.forEach(toggle => {
        toggle.addEventListener('change', function() {
            // Toggle state is handled by CSS
            console.log('Toggle changed:', this.checked);
        });
    });

    // Password visibility toggle (if implemented)
    const passwordInputs = document.querySelectorAll('input[type="password"]');
    passwordInputs.forEach(input => {
        // Add toggle button if needed
    });

    // Form submissions
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            // Prevent actual submission in prototype
            if (!form.hasAttribute('data-allow-submit')) {
                e.preventDefault();
                console.log('Form submitted (prototype - no backend)');
            }
        });
    });

    // Smooth scroll for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            const href = this.getAttribute('href');
            if (href !== '#') {
                e.preventDefault();
                const target = document.querySelector(href);
                if (target) {
                    target.scrollIntoView({ behavior: 'smooth' });
                }
            }
        });
    });

    // Add loaded class for animations
    document.body.classList.add('loaded');
});

// Utility functions
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Get URL query parameter
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

// Set URL query parameter
function setQueryParam(param, value) {
    const url = new URL(window.location);
    url.searchParams.set(param, value);
    window.history.pushState({}, '', url);
}

// Format date
function formatDate(date) {
    const now = new Date();
    const diff = now - date;
    const seconds = Math.floor(diff / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);

    if (days > 7) {
        return date.toLocaleDateString('zh-CN');
    } else if (days > 0) {
        return days + '天前';
    } else if (hours > 0) {
        return hours + '小时前';
    } else if (minutes > 0) {
        return minutes + '分钟前';
    } else {
        return '刚刚';
    }
}

// Console welcome message
console.log('%c GeekHour Prototype ', 'background: #3b82f6; color: white; padding: 5px 10px; border-radius: 4px;');
console.log('%c 编程学习平台页面原型 ', 'color: #6b7280;');
