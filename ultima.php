
<?php 
function mostra_horarios ($horarios){
	$i = 0;
	while ($i < 3):
	    echo "<br />Sala ";
		echo  $i+1;		
	    echo "<br />N°| Hora | Disponibilidade <br />";		
	    echo "	    01        |13-14 |".(($horarios[$i][0]==0) ? 'disponivel<br />' : 'ocupado<br />');
	    echo "	    02        |14-15 |".(($horarios[$i][1]==0) ? 'disponivel<br />' : 'ocupado<br />');
	    echo "	    03        |15-16 |".(($horarios[$i][2]==0) ? 'disponivel<br />' : 'ocupado<br />');
	    echo "	    04        |16-17 |".(($horarios[$i][2]==0) ? 'disponivel<br />' : 'ocupado<br />');
	    $i++;
	endwhile;


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
	mostra_horarios ($horarios);
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
	mostra_horarios ($horarios);
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
        echo "mostar os horarios";
		mostra_horarios ($_SESSION['horarios']);
        break; 	
    case 2:
        echo "Reservar horarios";
		$aa=intval($_POST["sala"]);
		$bb=intval($_POST["horario"]);
		//echo $aa.$bb;
		reservar_horarios($aa,$bb,$_SESSION['horarios']);
        break;
    case 3:
        echo "Cancelar reserva de horarios";
		cancelar_reserva($_POST["sala"],$_POST["horario"],$_SESSION['horarios']);
        break;   
}


?>
<form>
<input type="button" value="Voltar" onClick="JavaScript: window.history.back();">
