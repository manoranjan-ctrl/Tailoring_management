/**
 * ONLINE TAILORING MANAGEMENT SYSTEM
 * Client API & Session Controller
 */

const API_BASE = '/api';

// Toast Notifications
function showToast(message, type = 'info') {
    let container = document.getElementById('toast-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        document.body.appendChild(container);
    }

    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.innerHTML = `
        <div style="display:flex; justify-content:space-between; align-items:center; gap:0.75rem;">
            <span>${message}</span>
            <button onclick="this.closest('.toast').remove()" style="background:none;border:none;color:#94a3b8;cursor:pointer;font-size:1.1rem;line-height:1;">&times;</button>
        </div>
    `;
    container.appendChild(toast);

    setTimeout(() => {
        if (toast.parentElement) {
            toast.remove();
        }
    }, 4000);
}

// User Session Management
function getCurrentUser() {
    const userJson = localStorage.getItem('tailor_user');
    try {
        return userJson ? JSON.parse(userJson) : null;
    } catch (e) {
        return null;
    }
}

function setCurrentUser(user) {
    localStorage.setItem('tailor_user', JSON.stringify(user));
}

function logout() {
    localStorage.removeItem('tailor_user');
    window.location.href = 'login.html';
}

function requireAuth() {
    const user = getCurrentUser();
    if (!user) {
        window.location.href = 'login.html';
        return null;
    }
    return user;
}

// Strict Master/Admin Route Guard
function requireMaster() {
    const user = requireAuth();
    if (!user) return null;

    if (user.role !== 'MASTER' && user.role !== 'ADMIN') {
        alert('Access Denied: Master Tailor Authorization Required.');
        window.location.href = 'dashboard.html';
        return null;
    }
    return user;
}

function requireAdmin() {
    return requireMaster();
}

// Unified REST API Dispatcher
async function apiCall(endpoint, method = 'GET', data = null) {
    const options = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    };

    if (data && (method === 'POST' || method === 'PUT' || method === 'PATCH')) {
        options.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(`${API_BASE}${endpoint}`, options);
        const json = await response.json().catch(() => ({}));

        if (!response.ok) {
            const errorMsg = json.message || json.error || `Error ${response.status}`;
            throw new Error(errorMsg);
        }

        return json;
    } catch (error) {
        console.error(`API [${method} ${endpoint}] error:`, error);
        throw error;
    }
}

// Dynamic Navigation Header
function initNavbar() {
    const placeholder = document.getElementById('navbar-placeholder');
    if (!placeholder) return;

    const user = getCurrentUser();
    const currentPage = window.location.pathname.split('/').pop() || 'index.html';

    let navLinks = '';
    if (user) {
        const isMaster = (user.role === 'MASTER' || user.role === 'ADMIN');
        navLinks = `
            <li><a href="dashboard.html" class="nav-item-link ${currentPage === 'dashboard.html' ? 'active' : ''}">Dashboard</a></li>
            <li><a href="custom-design.html" class="nav-item-link ${currentPage === 'custom-design.html' ? 'active' : ''}">Custom Design</a></li>
            <li><a href="measurements.html" class="nav-item-link ${currentPage === 'measurements.html' ? 'active' : ''}">Measurements</a></li>
            <li><a href="orders.html" class="nav-item-link ${currentPage === 'orders.html' ? 'active' : ''}">Orders</a></li>
            <li><a href="javascript:void(0)" onclick="openProfileModal()" class="nav-item-link">Profile</a></li>
            ${isMaster ? `<li><a href="admin.html" class="nav-item-link ${currentPage === 'admin.html' ? 'active' : ''}">Master Portal</a></li>` : ''}
            <li><span class="user-pill ${isMaster ? 'admin-pill' : ''}">${user.name}</span></li>
            <li><button onclick="logout()" class="btn btn-outline btn-sm">Logout</button></li>
        `;
    } else {
        navLinks = `
            <li><a href="index.html" class="nav-item-link ${currentPage === 'index.html' ? 'active' : ''}">Home</a></li>
            <li><a href="login.html" class="nav-item-link ${currentPage === 'login.html' ? 'active' : ''}">Login</a></li>
            <li><a href="register.html" class="btn btn-primary btn-sm">Get Started</a></li>
        `;
    }

    placeholder.innerHTML = `
        <header class="site-header">
            <div class="nav-inner">
                <a href="${user ? 'dashboard.html' : 'index.html'}" class="brand-link">
                    <div class="brand-monogram">&#9986;</div>
                    <div class="brand-titles">
                        <span class="brand-name">Master Tailor</span>
                        <span class="brand-sub">Bespoke Tailoring Studio</span>
                    </div>
                </a>
                <ul class="nav-menu">
                    ${navLinks}
                </ul>
            </div>
        </header>
    `;
}

// Profile Modal trigger from header
function openProfileModal() {
    const editModal = document.getElementById('edit-profile-modal');
    if (editModal) {
        openModal('edit-profile-modal');
    } else {
        window.location.href = 'dashboard.html#profile';
    }
}

// Modal Helpers
function openModal(id) {
    const el = document.getElementById(id);
    if (el) {
        el.classList.add('open');
    }
}

function closeModal(id) {
    const el = document.getElementById(id);
    if (el) {
        el.classList.remove('open');
    }
}

document.addEventListener('DOMContentLoaded', initNavbar);
