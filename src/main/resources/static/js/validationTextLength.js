document.addEventListener("DOMContentLoaded", () => {

    const titleText = document.getElementById("title");
    const contentText = document.getElementById("content");

    const textLimitFilter = (text, maxLength) => {
        text.addEventListener("input", () => {
                const currentText = text.value;
                if (currentText.length > maxLength) {
                    text.value = currentText.substring(0, maxLength-1);
                    alert(`최대 ${maxLength}자 까지만 입력이 가능합니다.`);
                }
            }
        )
    }
    textLimitFilter(titleText,20);
    textLimitFilter(contentText,20);
});