// Пользователи
async function loadUsers() {
    const data = await api('GET', '/api/users');
    const body = document.getElementById('users-table-body');
    if (!data) return;
    const rows = data.map(u => `
    <tr>
      <td><span class="id-badge">#${u.user_id}</span></td>
      <td>${u.userName}</td>
      <td><span class="badge badge-blue">${(u.playlists || []).length} playlists</span></td>
      <td><span class="badge badge-green">${(u.favouriteArtists || []).length} artists</span></td>
      <td class="actions">
        <button class="btn btn-ghost btn-sm" onclick='editUser(${u.user_id}, "${escQ(u.userName)}")'>Edit</button>
        <button class="btn btn-danger btn-sm" onclick="deleteUser(${u.user_id})">Delete</button>
      </td>
    </tr>`);
    body.innerHTML = buildTable(['ID', 'Username', 'Playlists', 'Fav. Artists', 'Actions'], rows);
}

async function createUser() {
    const name = document.getElementById('u-name').value.trim();
    if (!name) return;
    await api('POST', '/api/users', { userName: name });
    document.getElementById('u-name').value = '';
    toast('User created');
    loadUsers();
}

function editUser(id, name) {
    openModal('Edit User', 'Username', name, async val => {
        await api('PATCH', '/api/users/' + id, { userName: val });
        toast('User updated');
        loadUsers();
    });
}

async function deleteUser(id) {
    if (!confirm('Delete this user?')) return;
    await api('DELETE', '/api/users/' + id);
    toast('User deleted');
    loadUsers();
}

// Плейлисты
async function loadPlaylists() {
    const data = await api('GET', '/api/playlists');
    const body = document.getElementById('playlists-table-body');
    if (!data) return;
    const rows = data.map(p => `
    <tr>
      <td><span class="id-badge">#${p.playlist_id}</span></td>
      <td>${p.playlistName}</td>
      <td><span class="badge badge-green">User #${p.user ? p.user.user_id : '?'}</span></td>
      <td class="actions">
        <button class="btn btn-ghost btn-sm" onclick='editPlaylist(${p.playlist_id}, "${escQ(p.playlistName)}")'>Edit</button>
        <button class="btn btn-danger btn-sm" onclick="deletePlaylist(${p.playlist_id})">Delete</button>
      </td>
    </tr>`);
    body.innerHTML = buildTable(['ID', 'Name', 'Owner', 'Actions'], rows);
}

async function createPlaylist() {
    const name = document.getElementById('pl-name').value.trim();
    const uid = document.getElementById('pl-user-id').value;
    if (!name || !uid) return;
    await api('POST', '/api/playlists', {
        playlistName: name,
        user: { user_id: parseInt(uid) }
    });
    document.getElementById('pl-name').value = '';
    document.getElementById('pl-user-id').value = '';
    toast('Playlist created');
    loadPlaylists();
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