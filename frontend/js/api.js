let BASE_URL = 'http://localhost:7979';

function setBaseUrl(v) {
    BASE_URL = v.replace(/\/$/, '');
}

async function api(method, path, body) {
    try {
        const opts = { method, headers: { 'Content-Type': 'application/json' } };
        if (body) opts.body = JSON.stringify(body);
        const res = await fetch(BASE_URL + path, opts);
        if (!res.ok) throw new Error('HTTP ' + res.status);
        if (res.status === 204) return null;
        return await res.json();
    } catch(e) {
        toast('Error: ' + e.message, 'error');
        throw e;
    }
}