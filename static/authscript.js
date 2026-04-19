const BASE_URL = "http://localhost:8081/api";

// REGISTER FUNCTION
async function register() {
    const username = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;

    if(!username || !password) { alert("Fields cannot be empty"); return; }

    try {
        const response = await fetch(`${BASE_URL}/auth/register`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            alert("Registered successfully!");
            window.location.href = "login.html";
        } else {
            alert("Registration failed. User might already exist.");
        }
    } catch (error) {
        console.error("Connection Error:", error);
    }
}

// LOGIN FUNCTION
async function login() {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    try {
        const response = await fetch(`${BASE_URL}/auth/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            const token = await response.text(); // Get JWT string
            localStorage.setItem("token", token);
            localStorage.setItem("user", username);
            window.location.href = "dash.html";
        } else {
            alert("Login failed! Check credentials.");
        }
    } catch (error) {
        console.error("Login Error:", error);
    }
}