// Экранирование кавычек для передачи в onclick
function escQ(s) {
    return s.replace(/"/g, '&quot;').replace(/'/g, "\\'");
}

// Построитель таблиц
function buildTable(headers, rows) {
    if (!rows.length) return '<div class="empty"><p>No records found. Create one above.</p></div>';
    return `<table><thead><tr>${headers.map(h => '<th>' + h + '</th>').join('')}</tr></thead><tbody>${rows.join('')}</tbody></table>`;
}