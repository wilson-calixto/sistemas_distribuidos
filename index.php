<form action="" method="post">

 <p>Digite a opcao desejada: <input type="text" name="opcao" /></p>
 <p>Numero da sala: <input type="text" name="sala" /></p>
 <p>Numero do horario: <input type="text" name="horario" /></p>
 <p><input type="submit" /></p>
</form>

<?php


$data = array('opcao'=>$_POST["opcao"],
'sala'=>$_POST["sala"],
'horario'=>$_POST["horario"]);
	
$url = 'http://localhost:8080';
$channel = curl_init();
curl_setopt($channel, CURLOPT_URL, $url);
curl_setopt($channel, CURLOPT_RETURNTRANSFER, true);
curl_setopt($channel, CURLOPT_POST, true);
curl_setopt($channel, CURLOPT_POSTFIELDS, $data);

// Acessar a URL e retornar a saída
$output = curl_exec($channel);


// Imprimer a saída
print_r (json_decode($output));

// liberar
curl_close($channel);
?>

