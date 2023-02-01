function createUI() {
    return new Promise(async resolve => {
        await gui.generateGui();
        await itemWindow.loadItems();
        await headIndex.loadEverything();

        createListeners();
        gui.renderGui();
        gui.renderStateProperties();
        resolve();
    });
}

function createListeners() {
    document.getElementById("export-gui-button")
        .addEventListener("click", () => {
            downloadGui(gui.exportGui(), "text/plain", gui.content.guiId + ".gui")
                .then(r => Toast.show("Exported command into gui file", "success"))
                .catch(err => {
                    console.log(err);
                    Toast.show("Failed exporting your gui", "error")
                });
        });

    document.getElementById("version-button")
        .addEventListener("click", () => {
            navigator.clipboard.writeText(document.getElementById("version-button").innerText)
                .then(r => Toast.show("Copied version into clipboard", "success"))
                .catch(e => {
                    console.error(e);
                    Toast.show("Error occurred while coping to clipboard", "error")
                });
            gui.exportGui();
        });
}

function downloadGui(content, mimeType, filename){
    return new Promise(resolve => {
        const a = document.createElement('a');
        const blob = new Blob([content], {type: mimeType});
        const url = URL.createObjectURL(blob);
        a.setAttribute('href', url);
        a.setAttribute('download', filename);
        a.click();
        resolve();
    });
}

function htmlToElement(html) {
    const template = document.createElement('template');
    html = html.trim();
    template.innerHTML = html;
    return template.content.firstChild;
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function capitalizeBySpace(string) {
    return string.split(" ")
        .map(x => x.charAt(0).toUpperCase() + x.slice(1))
        .reduce((previousValue, currentValue) => previousValue + currentValue);
}

function makeDraggableItem(htmlElement, isHead, idSupplier, customDataSupplier) {
    htmlElement.addEventListener("dragstart", e => {
        e.dataTransfer.setData("text/plain", idSupplier() + ";;;" + isHead + ";;;" + customDataSupplier());
    });
}

const rowElement = document.getElementById("rows");

const itemWindow = new ItemWindow();
const gui = new Gui();

createUI()
    .then(() => Toast.show("Editor loaded successfully", "success"))
    .catch(() => Toast.show("Editor didn't load correctly", "error"));
