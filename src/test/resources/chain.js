document.addEventListener("DOMContentLoaded", function () {
    const themeToggle = document.createElement("button");
    themeToggle.innerText = "Toggle Theme";
    themeToggle.className = "theme-toggle";
    document.body.appendChild(themeToggle);

    themeToggle.addEventListener("click", function () {
        document.body.classList.toggle("dark-mode");
        localStorage.setItem("theme", document.body.classList.contains("dark-mode") ? "dark" : "light");
    });

    if (localStorage.getItem("theme") === "dark") {
        document.body.classList.add("dark-mode");
    }

    // Collapsible Sections
    document.querySelectorAll(".collapsible").forEach(button => {
        button.addEventListener("click", function () {
            this.classList.toggle("active");
            let content = this.nextElementSibling;
            content.style.display = content.style.display === "block" ? "none" : "block";
        });
    });

    // Smooth Scrolling
    const scrollButton = document.createElement("button");
    scrollButton.innerText = "Scroll to Top";
    scrollButton.className = "scroll-top";
    document.body.appendChild(scrollButton);

    window.addEventListener("scroll", function () {
        scrollButton.style.display = window.scrollY > 200 ? "block" : "none";
    });

    scrollButton.addEventListener("click", function () {
        window.scrollTo({ top: 0, behavior: "smooth" });
    });
});
