async function loadArtists() {
    const data = await api('GET', '/api/artists');
    const body = document.getElementById('artists-table-body');
    if (!data) return;
    const rows = data.map(a => `
    <tr>
      <td><span class="id-badge">#${a.artist_id}</span></td>
      <td>${a.artistName}</td>
      <td class="actions">
        <button class="btn btn-ghost btn-sm" onclick='editArtist(${a.artist_id}, "${escQ(a.artistName)}")'>Edit</button>
        <button class="btn btn-danger btn-sm" onclick="deleteArtist(${a.artist_id})">Delete</button>
      </td>
    </tr>`);
    body.innerHTML = buildTable(['ID', 'Artist Name', 'Actions'], rows);
}

async function createArtist() {
    const name = document.getElementById('a-name').value.trim();
    if (!name) return;
    await api('POST', '/api/artists', { artistName: name });
    document.getElementById('a-name').value = '';
    toast('Artist created');
    loadArtists();
}

function editArtist(id, name) {
    openModal('Edit Artist', 'Artist Name', name, async val => {
        await api('PATCH', '/api/artists/' + id, { artistName: val });
        toast('Artist updated');
        loadArtists();
    });
}

async function deleteArtist(id) {
    if (!confirm('Delete this artist?')) return;
    await api('DELETE', '/api/artists/' + id);
    toast('Artist deleted');
    loadArtists();
}