<?php

try{
	if($_SERVER['REQUEST_METHOD'] !== 'POST'){
		throw new Exception('Metodo nao he suportado', 405);
	}
	
	$data = array(
		'status' => 200,
		'message' => 'Requisicao chegou com sucesso',
		'data' => $_POST["opcao"]
	);
	
	print json_encode($data);

}catch(Exception $e){
	
	$data = array(
		'status' => $e->getCode(),
		'message' => $e->getMessage(),
	);
	
	print json_encode($data);
}

function envia_horarios (){
	
	$data = array(
		'status' => 200,
		'message' => 'Requisicao chegou com sucesso',
		'horarios' => $_SESSION['horarios']
	);
	
	print json_encode($data);
}

function reservar_horarios ($n_sala,$n_horario,$horarios){
	
	$n_sala=$n_sala-1;
	$n_horario=$n_horario-1;
	if ($horarios[$n_sala][$n_horario]==0) {
		 $horarios[$n_sala][$n_horario]=1;
		 echo "<br />Horario reservado com sucesso<br />";
	} else {
	   echo "<br />Horario indisponivel<br />";				
	}	
 	envia_horarios();
	$_SESSION['horarios'] = $horarios;
}

function cancelar_reserva($n_sala,$n_horario,$horarios){
	$n_sala=$n_sala-1;
	$n_horario=$n_horario-1;
	if ($horarios[$n_sala][$n_horario]==1) {
		$horarios[$n_sala][$n_horario]=0;
		echo "<br />Cancelamento de reserva efetuado com sucesso<br />";
	} else {
	    echo "<br />O Horario ja esta disponivel<br />";				
	}	
 	envia_horarios();
	$_SESSION['horarios'] = $horarios;
}


session_start();

if (!isset($_SESSION['horarios'])){
			$horarios= array(
			array(0,0,0,0),
			array(1,1,1,1),
			array(0,1,0,1));
			$_SESSION['horarios'] = $horarios;
}
switch ($_POST["opcao"]) {
    case 1:
       	envia_horarios();
        break; 	
    case 2:
				reservar_horarios($_POST["sala"],$_POST["horario"],$_SESSION['horarios']);
        break;
    case 3:
				cancelar_reserva($_POST["sala"],$_POST["horario"],$_SESSION['horarios']);
        break;   
}


