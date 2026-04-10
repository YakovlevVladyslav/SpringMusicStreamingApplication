async function loadSongs() {
    const data = await api('GET', '/api/songs');
    const body = document.getElementById('songs-table-body');
    if (!data) return;
    const rows = data.map(s => `
    <tr>
      <td><span class="id-badge">#${s.song_id}</span></td>
      <td>${s.songName}</td>
      <td>${s.album ? '<span class="badge badge-blue">' + s.album.albumName + '</span>' : '<span style="color:var(--muted)">—</span>'}</td>
      <td class="actions">
        <button class="btn btn-ghost btn-sm" onclick='editSong(${s.song_id}, "${escQ(s.songName)}")'>Edit</button>
        <button class="btn btn-danger btn-sm" onclick="deleteSong(${s.song_id})">Delete</button>
      </td>
    </tr>`);
    body.innerHTML = buildTable(['ID', 'Song Name', 'Album', 'Actions'], rows);
}

async function createSong() {
    const name = document.getElementById('s-name').value.trim();
    if (!name) return;
    await api('POST', '/api/songs', { songName: name });
    document.getElementById('s-name').value = '';
    toast('Song created');
    loadSongs();
}

function editSong(id, name) {
    openModal('Edit Song', 'Song Name', name, async val => {
        await api('PATCH', '/api/songs/' + id, { songName: val });
        toast('Song updated');
        loadSongs();
    });
}

async function deleteSong(id) {
    if (!confirm('Delete this song?')) return;
    await api('DELETE', '/api/songs/' + id);
    toast('Song deleted');
    loadSongs();
}