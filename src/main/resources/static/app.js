// Pobranie elementu logów
const logs = document.getElementById("logs");

// Funkcja dodająca nowy wpis do logów
function appendLog(text, type) {
    const logLine = document.createElement("div");
    logLine.textContent = text;

    // Dodanie klasy CSS w zależności od typu
    if (type === "alert") logLine.classList.add("alert-log");
    if (type === "message") logLine.classList.add("message-log");

    logs.appendChild(logLine);
    logs.scrollTop = logs.scrollHeight; // przewijanie do dołu
}

// Wysłanie alertu do backendu
function sendAlert() {
    const msg = document.getElementById("alertInput").value;
    fetch(`/api/alert/sendAlert?msg=${encodeURIComponent(msg)}`, { method: "POST" })
        .then(response => response.text())
        .then(data => appendLog(data, "alert"))
        .catch(err => appendLog("Error: " + err));
}

// Wysłanie message do backendu
function sendMessage() {
    const msg = document.getElementById("messageInput").value;
    fetch(`/api/alert/sendMessage?msg=${encodeURIComponent(msg)}`, { method: "POST" })
        .then(response => response.text())
        .then(data => appendLog(data, "message"))
        .catch(err => appendLog("Error: " + err));
}
