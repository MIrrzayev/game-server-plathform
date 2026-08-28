const token = localStorage.getItem("token");
const registerForm = document.getElementById("registerForm");
if (registerForm) {
    registerForm.addEventListener("submit", async function (event) {
        event.preventDefault();
        const username = document.getElementById("username").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;
        const errorMessage = document.getElementById("errorMessage");
        if (password !== confirmPassword) {
            errorMessage.textContent = "Passwords do not match!";
            return;
        }
        try {
            const response = await fetch("http://localhost:8080/api/auth/register",
                {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify({ username: username, email: email, password: password})
                }
            );
            if (response.ok) {
                window.location.href = "newlogin.html";
            } else {
                errorMessage.textContent = "Registration failed!";
            }
        } catch (error) {
            console.error("Register error:", error);
            errorMessage.textContent = "Server connection failed!";
        }
    });
}
const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault();
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const errorMessage = document.getElementById("errorMessage");
        errorMessage.textContent = "";
        try {
            const response = await fetch( "http://localhost:8080/api/auth/login",
                {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify({email: email, password: password})
                }
            );
            console.log("Login status:", response.status);
            if (response.ok) {
                const data = await response.json();
                console.log("Login data:", data);
                localStorage.setItem( "token", data.token);
                console.log( "Token saved:", localStorage.getItem("token"));
                window.location.href = "index.html";
            } else {
                errorMessage.textContent = "Email or password is incorrect!";
            }
        } catch (error) {
            console.error("Login error:", error);
            errorMessage.textContent = "Server connection failed!";
        }
    });
}
const profilePage = document.getElementById("profilePage");
const homePage = document.getElementById("homePage");

if (homePage || profilePage) {
    const currentToken = localStorage.getItem("token");
    if (!currentToken) {
        window.location.href = "newlogin.html";
    } else {
        fetch("http://localhost:8080/api/users/me", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + currentToken
            }
        })
        .then(async response => {
            if (!response.ok) {
                throw new Error("Unauthorized");
            }
            return response.json();
        })
        .then(user => {
            console.log("Current user:", user);
            const usernameElement =
                document.getElementById("username");
            const profileUsernameElement =
                document.getElementById("profileUsername");
            const profileEmailElement =
                document.getElementById("profileEmail");
            if (usernameElement) {
                usernameElement.textContent = user.username;
            }
            if (profileUsernameElement) {
                profileUsernameElement.textContent = user.username;
            }
            if (profileEmailElement) {
                profileEmailElement.textContent = user.email;
            }
        })
        .catch(error => {
            console.error("Profile error:", error);
            localStorage.removeItem("token");
            window.location.href = "newlogin.html";
        });
    }
}
const logoutButton = document.getElementById("logoutButton");
if (logoutButton) {
    logoutButton.addEventListener("click", function (event) {
            event.preventDefault();
            localStorage.removeItem("token");
            window.location.href = "newlogin.html";
        }
    );
}
const gamesContainer = document.getElementById("gamesContainer");
const emptyGames = document.getElementById("emptyGames");
const addGameButton = document.getElementById("addGameButton");
if (gamesContainer) {
    const currentToken = localStorage.getItem("token");
    if (!currentToken) {
        window.location.href = "newlogin.html";
    } else {
        fetch("http://localhost:8080/api/user-games", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + currentToken
            }
        })
        .then(async response => {
            if (!response.ok) {
                throw new Error("Failed to load games");
            }
            return response.json();
        })
        .then(games => {
            console.log("User games:", games);
            if (games.length === 0) {
                return;
            }
            emptyGames.style.display = "none";
            games.forEach(game => {
                const gameCard = document.createElement("div");
                gameCard.className = "game-account-card";
                gameCard.innerHTML = `
                    <div class="game-account-info">
                        <div class="game-account-icon">
                            🎮
                        </div>
                        <div>
                            <h3>${game.gameName}</h3>
                            <p>${game.accountUsername}</p>
                        </div>
                    </div>
                    <span class="connected">
                        ✓ Connected
                    </span>
                `;
                gamesContainer.appendChild(gameCard);
            });
        })
        .catch(error => {

            console.error("Games error:", error);

        });
    }
}