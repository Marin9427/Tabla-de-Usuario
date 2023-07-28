// Call the dataTables jQuery plugin
$(document).ready(function() {

CargarUsuarios();

  $('#UsuariosTable').DataTable();
  actualizarEmail();
});
function actualizarEmail()
{
document.getElementById('txt-email-usuario').outerHTML=localStorage.email;
}


async function CargarUsuarios()
{
  const request = await fetch('api/Usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const Usuarios = await request.json();




let ListadoHtml='';
  for(let cilcoUsuarios of Usuarios ){

      let BotonEliminar='<a href="#" onclick="eliminarUsuario(' + cilcoUsuarios.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

  let Telefonotexto= cilcoUsuarios.telefono == null ? '--' : cilcoUsuarios.telefono;


  let Usuariohtml='<tr><td>' + cilcoUsuarios.id+ '</td><td>'+cilcoUsuarios.nombre +' ' + cilcoUsuarios.apellido +'</td> <td>'+ cilcoUsuarios.email+'</td><td>'+Telefonotexto+'</td><td>' + BotonEliminar  + '</td></tr>';
 ListadoHtml+=Usuariohtml;

 }
document.querySelector('#UsuariosTable tbody').outerHTML =ListadoHtml;
}



function getHeaders()
{
return  {'Accept': 'application/json',
             'Content-Type': 'application/json',
             'Authorization' : localStorage.token
};
}


async function eliminarUsuario(id)
{

if(!confirm('Desea eliminar este usuario?') )
{
return;
}

 const request = await fetch('api/Usuarios/' +id, {
    method: 'DELETE',
    headers: getHeaders()
  });

 location.reload()

}