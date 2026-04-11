// ==========================================
// СЕРВИС ПОЛЬЗОВАТЕЛЕЙ (USERS)
// ==========================================

async function loadUsers() {
    const data = await api('GET', '/api/users');
    console.log('Данные пользователей от сервера:', data);

    const body = document.getElementById('users-table-body');
    if (!data) return;

    const rows = data.map(u => {
        // Получаем ID пользователя
        const userId = u.user_id || u.id;
        
        // Рендерим избранных артистов (теперь это просто массив ID)
        const favsHtml = (u.favouriteArtists || []).map(artistId => {
            return `<span class="badge badge-amber" title="Artist ID">★ ${artistId}</span>`;
        }).join(' ');

        return `
        <tr>
          <td><span class="id-badge">#${userId}</span></td>
          <td>${u.userName || 'Unknown'}</td>
          <td><span class="badge badge-blue">${(u.playlists || []).length} плейлистов</span></td>
          <td>
            <div style="display: flex; gap: 4px; flex-wrap: wrap;">
                ${favsHtml || '<span style="color:var(--muted)">Нет избранных</span>'}
            </div>
          </td>
          <td class="actions">
            <button class="btn btn-ghost btn-sm" onclick='editUser(${userId}, "${escQ(u.userName)}")'>Edit</button>
            <button class="btn btn-danger btn-sm" onclick="deleteUser(${userId})">Delete</button>
          </td>
        </tr>`;
    });
    
    // Заполняем таблицу
    body.innerHTML = buildTable(['ID', 'Username', 'Playlists', 'Fav. Artists (ID)', 'Actions'], rows);
}

async function createUser() {
    const nameInput = document.getElementById('u-name');
    const favArtistInput = document.getElementById('u-fav-artist'); // Убедись, что такой ID есть в index.html
    
    const name = nameInput.value.trim();
    const favArtistId = favArtistInput ? favArtistInput.value : null;

    if (!name) {
        toast('Введите имя пользователя', 'error');
        return;
    }

    // Формируем JSON для отправки (массив объектов для Hibernate)
    const payload = { 
        userName: name,
        favouriteArtists: [] 
    };

    if (favArtistId) {
        payload.favouriteArtists.push({
            artist_id: parseInt(favArtistId)
        });
    }

    try {
        await api('POST', '/api/users', payload);
        
        // Очистка
        nameInput.value = '';
        if (favArtistInput) favArtistInput.value = '';
        
        toast('Пользователь создан');
        loadUsers();
    } catch (e) {
        console.error("Ошибка при создании:", e);
        toast('Ошибка создания (проверьте ID артиста)', 'error');
    }
}

// ... функции editUser и deleteUser остаются без изменений

function editUser(id, name) {
    openModal('Edit User', 'Username', name, async val => {
        try {
            // Отправляем ТОЛЬКО измененное поле
            const payload = { 
                userName: val 
            };
            
            console.log("Patching user:", id, payload);
            
            await api('PATCH', `/api/users/${id}`, payload);
            toast('User updated');
            loadUsers();
        } catch (e) {
            console.error("Patch error details:", e);
            toast('Failed to update user (Server Error 500)', 'error');
        }
    });
}

async function deleteUser(id) {
    if (!confirm(`Вы уверены, что хотите удалить пользователя #${id}?`)) return;

    try {
        await api('DELETE', `/api/users/${id}`);
        toast('Пользователь успешно удален');
        loadUsers(); // Перезагружаем список
    } catch (e) {
        console.error("Ошибка удаления:", e);
        
        // Проверяем, не конфликт ли это (409)
        if (e.message.includes('409')) {
            toast('Нельзя удалить: у пользователя есть активные плейлисты', 'error');
        } else {
            toast('Ошибка при удалении пользователя', 'error');
        }
    }
}

// ==========================================
// СЕРВИС ПЛЕЙЛИСТОВ (PLAYLISTS)
// ==========================================

async function loadPlaylists() {
    const data = await api('GET', '/api/playlists');
    console.log('Данные плейлистов от сервера:', data);

    const body = document.getElementById('playlists-table-body');
    if (!data) return;

    const rows = data.map(p => {
        // ID плейлиста из Playlist.java
        const playlistId = p.playlist_id || p.id;
        
        // Обработка владельца (может прийти как ID или как объект)
        let ownerDisplay = '?';
        if (p.user) {
            ownerDisplay = typeof p.user === 'object' 
                ? (p.user.user_id || p.user.id) 
                : p.user;
        }

        return `
        <tr>
          <td><span class="id-badge">#${playlistId}</span></td>
          <td>${p.playlistName || 'Untitled'}</td>
          <td><span class="badge badge-green">User #${ownerDisplay}</span></td>
          <td class="actions">
            <button class="btn btn-ghost btn-sm" onclick='editPlaylist(${playlistId}, "${escQ(p.playlistName)}")'>Edit</button>
            <button class="btn btn-danger btn-sm" onclick="deletePlaylist(${playlistId})">Delete</button>
          </td>
        </tr>`;
    });
    body.innerHTML = buildTable(['ID', 'Name', 'Owner', 'Actions'], rows);
}

async function createPlaylist() {
    const name = document.getElementById('pl-name').value.trim();
    const uid = document.getElementById('pl-user-id').value;
    
    if (!name || !uid) {
        toast('Please fill all fields', 'error');
        return;
    }

    // Формируем payload для PlaylistController
    const payload = {
        playlistName: name,
        user: { 
            // Используем user_id, так как он указан в @Id в User.java
            user_id: parseInt(uid) 
        }
    };

    try {
        await api('POST', '/api/playlists', payload);
        document.getElementById('pl-name').value = '';
        document.getElementById('pl-user-id').value = '';
        toast('Playlist created');
        loadPlaylists();
    } catch (e) {
        console.error("Error creating playlist:", e);
    }
}

function editPlaylist(id, name) {
    openModal('Edit Playlist', 'Name', name, async val => {
        await api('PATCH', '/api/playlists/' + id, { playlistName: val });
        toast('Playlist updated');
        loadPlaylists();
    });
}

async function deletePlaylist(id) {
    if (!confirm('Delete this playlist?')) return;
    await api('DELETE', '/api/playlists/' + id);
    toast('Playlist deleted');
    loadPlaylists();
}