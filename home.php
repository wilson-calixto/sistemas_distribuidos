
<?php
session_start();
echo " Digite 1 para visualizar as reservas de sala;<br /> Digite 2 para reservar uma sala;<br /> Digite 3 para cancelar uma reserva de sala";
?>

<form action="ultima.php" method="post">

 <p>Digite a opcao desejada: <input type="text" name="opcao" /></p>
 <p>Numero da sala: <input type="text" name="sala" /></p>
 <p>Numero do horario: <input type="text" name="horario" /></p>
 <p><input type="submit" /></p>
</form>
