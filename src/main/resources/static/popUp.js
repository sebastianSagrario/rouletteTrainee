let jugPUOpen=document.getElementById("btnSelJugada");
let jugPUClose=document.getElementById("cerrar-popUpJ");
let overlayJ=document.getElementById("overlayJ");

let coPUOpen=document.getElementById("btnSelCorte");
let coPUClose=document.getElementById("cerrar-popUpC");
let overlayC=document.getElementById("overlayC");


//dos event listeners
jugPUOpen.addEventListener("click", function()
{
    overlayJ.classList.add('active');
});

jugPUClose.addEventListener("click",function()
{
    overlayJ.classList.remove('active');
});

coPUOpen.addEventListener("click", function()
{
    overlayC.classList.add('active');
});

coPUClose.addEventListener("click",function()
{
    overlayC.classList.remove('active');
});

