async function loadAlbums() {
    const data = await api('GET', '/api/albums');
    const body = document.getElementById('albums-table-body');
    if (!data) return;
    const rows = data.map(al => `
    <tr>
      <td><span class="id-badge">#${al.album_id}</span></td>
      <td>${al.albumName}</td>
      <td>${al.artist ? '<span class="badge badge-amber">' + al.artist.artistName + '</span>' : '<span style="color:var(--muted)">—</span>'}</td>
      <td class="actions">
        <button class="btn btn-ghost btn-sm" onclick='editAlbum(${al.album_id}, "${escQ(al.albumName)}")'>Edit</button>
        <button class="btn btn-danger btn-sm" onclick="deleteAlbum(${al.album_id})">Delete</button>
      </td>
    </tr>`);
    body.innerHTML = buildTable(['ID', 'Album Name', 'Artist', 'Actions'], rows);
}

async function createAlbum() {
    const name = document.getElementById('al-name').value.trim();
    const aid = document.getElementById('al-artist-id').value;
    if (!name) return;
    const payload = { albumName: name };
    if (aid) payload.artist = { artist_id: parseInt(aid) };
    await api('POST', '/api/albums', payload);
    document.getElementById('al-name').value = '';
    document.getElementById('al-artist-id').value = '';
    toast('Album created');
    loadAlbums();
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