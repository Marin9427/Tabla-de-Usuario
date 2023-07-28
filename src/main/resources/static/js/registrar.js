// Call the dataTables jQuery plugin
$(document).ready(function() {

//

});

async function registratusuarioo()
{
let datos = {};
datos.nombre=document.getElementById('txtNombre').value;
datos.apellido=document.getElementById('txtApellido').value;
datos.email=document.getElementById('txtEmail').value;
datos.constrasena=document.getElementById('txtPassword').value;

let repitepassword=document.getElementById('txtRepetirP').value;

if(repitepassword != datos.constrasena)
{
alert('La contraseña es diferente');
return;
}


  const request = await fetch('api/Usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  alert("La cuenta fue creado con exito");
  window.location.href ='login.html'


}





