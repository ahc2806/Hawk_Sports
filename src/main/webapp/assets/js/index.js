//Función para inicializar Materialize JS
document.addEventListener('DOMContentLoaded', function() {
	M.AutoInit();
});

// Inicializar el modal de inicio de sesión
var modal = document.getElementById('id01');

// Cerrar el modal cuando le damos click
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}