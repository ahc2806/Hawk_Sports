//Función para inicializar Materialize JS
document.addEventListener('DOMContentLoaded', function() {
	M.AutoInit();
});


//Función para cambiar el título de la sección
function titulo(seccion) {
	document.getElementById('titulo').innerHTML = seccion;
}


//Funciones para cambiar el contenido del menú
//Inicio
function inicio() {
	$(document).ready(function() {
		$('.secciones').load('../inicio/inicio.xhtml');
	});
}


//Empleado
function empleado() {
	$(document).ready(function() {
		$('.secciones').load('../empleados/empleados.xhtml');
	});
}

function agregar_empleado() {
	$(document).ready(function() {
		$('.secciones').load('../empleados/agregar.xhtml');
	});
}

function editar_empleado() {
	$(document).ready(function() {
		$('.secciones').load('../empleados/editar.xhtml');
	});
}


//Usuarios
function usuario() {
	$(document).ready(function() {
		$('.secciones').load('../usuarios/usuarios.xhtml');
	});
}

function agregar_usuario() {
	$(document).ready(function() {
		$('.secciones').load('../usuarios/agregar.xhtml');
	});
}

function editar_usuario() {
	$(document).ready(function() {
		$('.secciones').load('../usuarios/editar.xhtml');
	});
}


//Clientes
function cliente() {
	$(document).ready(function() {
		$('.secciones').load('../clientes/clientes.xhtml');
	});
}

function agregar_cliente() {
	$(document).ready(function() {
		$('.secciones').load('../clientes/agregar.xhtml');
	});
}

function editar_cliente() {
	$(document).ready(function() {
		$('.secciones').load('../clientes/editar.xhtml');
	});
}


//Proveedores
function proveedor() {
	$(document).ready(function() {
		$('.secciones').load('../proveedores/proveedores.xhtml');
	});
}

function agregar_proveedor() {
	$(document).ready(function() {
		$('.secciones').load('../proveedores/agregar.xhtml');
	});
}

function editar_proveedor() {
	$(document).ready(function() {
		$('.secciones').load('../proveedores/editar.xhtml');
	});
}


//Productos
function producto() {
	$(document).ready(function() {
		$('.secciones').load('../productos/productos.xhtml');
	});
}

function agregar_producto() {
	$(document).ready(function() {
		$('.secciones').load('../productos/agregar.xhtml');
	});
}

function editar_producto() {
	$(document).ready(function() {
		$('.secciones').load('../productos/editar.xhtml');
	});
}