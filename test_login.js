const fetch = require('node-fetch');

async function login() {
    const res = await fetch('http://localhost:8085/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email: 'priyanshu@uni.edu', password: '123456' })
    });
    
    console.log("Status:", res.status);
    const text = await res.text();
    console.log("Body:", text);
}

login();
