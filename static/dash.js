const BASE_URL = "http://localhost:8081/api";
const token = localStorage.getItem("token");
const user = localStorage.getItem("user");

// Redirect if not logged in
if (!token) {
    window.location.href = "login.html";
}

async function submitScore() {
    const scoreInput = document.getElementById("score");
    const score = scoreInput.value;

    if(!score) {
        alert("Please enter a score");
        return;
    }

    try {
        const res = await fetch(`${BASE_URL}/leaderboard/submit`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({
                username: user,
                score: parseFloat(score)
            })
        });

        if (res.ok) {
            alert("Score submitted to Redis!");
            scoreInput.value = ""; // Clear input
            getLeaderboard(); // Refresh list
        } else {
            alert("Failed to submit score. Check console.");
        }
    } catch (err) {
        console.error("Submit Error:", err);
    }
}

async function getLeaderboard() {
    const list = document.getElementById("leaderboardList");

    try {
        const res = await fetch(`${BASE_URL}/leaderboard/top`, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            }
        });

        if (!res.ok) throw new Error("Fetch failed");

        const data = await res.json();
        list.innerHTML = ""; // Clear current list

        data.forEach((p, index) => {
            const li = document.createElement("li");
            // Redis sorted sets return 'value' and 'score'
            const name = p.value || "User";
            const points = p.score || 0;
            li.innerHTML = `<strong>#${index + 1}</strong> ${name} — ${points} pts`;
            list.appendChild(li);
        });
    } catch (err) {
        console.error("Leaderboard Error:", err);
    }
}

function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}

// Automatically load the leaderboard when the page opens
// This is how you call an async function correctly at the top level
window.onload = () => {
    getLeaderboard();
};

