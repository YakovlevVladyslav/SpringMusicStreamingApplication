let editContext = null;

function switchPage(name) {
    document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
    document.querySelectorAll('.nav-item').forEach(n => n.classList.remove('active'));
    document.getElementById('page-' + name).classList.add('active');

    // Подсветка активного пункта меню
    if (event && event.currentTarget) {
        event.currentTarget.classList.add('active');
    }

    const loaders = {
        songs: loadSongs,
        artists: loadArtists,
        albums: loadAlbums,
        playlists: loadPlaylists,
        users: loadUsers
    };
    if (loaders[name]) loaders[name]();
}

function toast(msg, type = 'success') {
    const c = document.getElementById('toasts');
    const t = document.createElement('div');
    t.className = 'toast ' + type;
    t.innerHTML = (type === 'success' ? '✓' : '✗') + ' <span>' + msg + '</span>';
    c.appendChild(t);
    setTimeout(() => t.remove(), 3500);
}

function openModal(title, field, value, ctx) {
    editContext = ctx;
    document.getElementById('modal-title').textContent = title;
    document.getElementById('modal-field-label').textContent = field;
    document.getElementById('modal-input').value = value;
    document.getElementById('editModal').classList.add('open');
}

function closeModal() {
    document.getElementById('editModal').classList.remove('open');
    editContext = null;
}

async function submitEdit() {
    const val = document.getElementById('modal-input').value.trim();
    if (!val) return;
    await editContext(val);
    closeModal();
}

// Закрытие по клику на фон
document.getElementById('editModal').addEventListener('click', e => {
    if (e.target === e.currentTarget) closeModal();
});