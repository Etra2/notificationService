const logs = document.getElementById("logs");

// Dodawanie wpisów do logów
function appendLog(text, type, priority = '') {
    const logLine = document.createElement("div");
    logLine.textContent = priority ? `[${priority}] ${text}` : text;

    if(type === "ALERT") logLine.classList.add("alert-log");
    if(type === "MESSAGE") logLine.classList.add("message-log");

    logs.appendChild(logLine);
    logs.scrollTop = logs.scrollHeight;
}

// Wysyłanie alertu (AlertController)
function sendAlert() {
    const content = document.getElementById("alertContent").value;

    fetch(`/api/alert/sendAlert?msg=${encodeURIComponent(content)}`, { method: "POST" })
        .then(response => response.text())
        .then(data => appendLog(data, "ALERT"))
        .catch(err => appendLog("Error: " + err));
}

// Wysyłanie wiadomości (MessageController)
function sendMessage() {
    const content = document.getElementById("messageContent").value;
    const priority = document.getElementById("messagePrioritySelect").value;

    const requestBody = {
        content: content,
        type: "MESSAGE",        // typ dla MessageController jest zawsze MESSAGE
        priority: priority
    };

    fetch(`/api/message`, {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestBody)
    })
        .then(response => response.text())
        .then(data => {
            // Wyświetlamy treść wiadomości w logach razem z priorytetem
            appendLog(`${content} | ${data}`, "MESSAGE", priority);
        })
        .catch(err => appendLog("Error: " + err));
}
