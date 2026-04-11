// ==========================================
// СЕРВИС АЛЬБОМОВ (ALBUMS)
// ==========================================

async function loadAlbums() {
    const data = await api('GET', '/api/albums');
    console.log('Данные альбомов от сервера:', data);

    const body = document.getElementById('albums-table-body');
    if (!data) return;

    const rows = data.map(al => {
        // ID альбома (по названию поля в Album.java)
        const albumId = al.album_id || al.id;
        
        // Теперь al.artist — это просто число (ID), так как мы настроили IdentityReference
        const artistId = al.artist;

        return `
        <tr>
          <td><span class="id-badge">#${albumId}</span></td>
          <td>${al.albumName || 'Untitled'}</td>
          <td>
            ${artistId 
                ? `<span class="badge badge-amber">Artist #${artistId}</span>` 
                : '<span style="color:var(--muted)">— No Artist —</span>'}
          </td>
          <td class="actions">
            <button class="btn btn-ghost btn-sm" onclick='editAlbum(${albumId}, "${escQ(al.albumName)}")'>Edit</button>
            <button class="btn btn-danger btn-sm" onclick="deleteAlbum(${albumId})">Delete</button>
          </td>
        </tr>`;
    });
    body.innerHTML = buildTable(['ID', 'Album Name', 'Artist ID', 'Actions'], rows);
}

async function createAlbum() {
    const name = document.getElementById('al-name').value.trim();
    const aid = document.getElementById('al-artist-id').value;
    
    if (!name) {
        toast('Please enter album name', 'error');
        return;
    }

    // Собираем объект для отправки на бэкенд
    const payload = { 
        albumName: name 
    };

    if (aid) {
        // Отправляем объект с artist_id, чтобы Hibernate правильно связал сущности
        payload.artist = { 
            artist_id: parseInt(aid) 
        };
    }

    try {
        await api('POST', '/api/albums', payload);
        document.getElementById('al-name').value = '';
        document.getElementById('al-artist-id').value = '';
        toast('Album created');
        loadAlbums();
    } catch (e) {
        console.error("Server error during creation:", e);
    }
}

function editAlbum(id, name) {
    openModal('Edit Album', 'Album Name', name, async val => {
        await api('PATCH', '/api/albums/' + id, { albumName: val });
        toast('Album updated');
        loadAlbums();
    });
}

async function deleteAlbum(id) {
    if (!confirm('Delete this album?')) return;
    await api('DELETE', '/api/albums/' + id);
    toast('Album deleted');
    loadAlbums();
}