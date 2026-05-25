package ROBO_TESTE;
import robocode.*;
import robocode.util.Utils;

//import java.awt.Color;

public class RoboTeste2 extends AdvancedRobot
{
	public void run() {
	//Radar e arma independentes do corpo
	setAdjustGunForRobotTurn(true);
    setAdjustRadarForGunTurn(true);
	//Movimentação
	while(true){
		setAhead(100);
		setTurnRight(90);
		setTurnRadarRight(360);
		execute();
		}
	}
	//Quando scannear um robo/ataque
	public void onScannedRobot(ScannedRobotEvent e) {
		//Ângulo do inimigo
        double anguloInimigo = getHeadingRadians() + e.getBearingRadians();

        //Ajuste do arma ao inimigo
        double ajusteArma = Utils.normalRelativeAngle(anguloInimigo - getGunHeadingRadians());

        //Mira trava no inimigo
        setTurnGunRightRadians(ajusteArma);

        //Radar segue o inimigo
        double 
ajusteRadar=Utils.normalRelativeAngle(anguloInimigo-getRadarHeadingRadians());setTurnRadarRightRadians(ajusteRadar);
		
		double distancia = e.getDistance();
			if (distancia < 200){
    			setFire(3);
			}
			else if (distancia < 600) {
    			setFire(2);
			}
			else {
    			setFire(1);
			}
        execute();
        // Atira quando estiver alinhado
        //if (Math.abs(getGunTurnRemaining()) < 5) {
           // fire(2);
		//}
	}

	//Quando atingido
	public void onHitByBullet(HitByBulletEvent e) {
		setAhead(60);
		setTurnLeft(90);
		execute();
		//back(10);
	}
	
	//Quando acertar uma parede
	public void onHitWall(HitWallEvent e) {
		setBack(20);
		setTurnRight(70);
		execute();
	}	
}
