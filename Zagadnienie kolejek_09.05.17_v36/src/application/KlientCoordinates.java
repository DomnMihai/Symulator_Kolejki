package application;

public enum KlientCoordinates {
	
//	windowWIDTH(1152.0f),
//	windwHEIGHT(648.0f),
	
//	p0 0 216
//	p1 640 216
//	p2 700 216
//	p3 700 166
//	p4 195 166
//	p5 195 71
	
	Distance(105),
	Time(600),
	
	StartPositionX(-30),
	StartPositionY(216),
	
	Line0X(33),
	PositionInLineX(45),
	
	CrossPositionX(730),
	DistanceToCross(67),
	
	AfterCrossPositionY(50),
	
	Kasa1X(645),
	Kasa3X(540),
	Kasa5X(435),
	Kasa7X(330),
	Kasa9X(225),
	Kasa11X(120),
	DistanceToKasa1(85),
	DistanceToKasy(105),
	
	EnterInKasaPositionY(145),
	EnterInKasaDistance(95),
	
	QuitKasaPositionY(185),
	QuitKasaDistance(40);
	
//	LineP1X(663),
//	LineP2X(518),
//	LineP3X(573),
//	LineP4X(528),
//	LineP5X(483),
//	LineP6X(438),
//	LineP7X(393),
//	LineP8X(348),
//	LineP9X(303),
//	LineP10X(258),
//	LineP11X(213),
//	LineP12X(168),
//	LineP13X(123),
//	LineP14X(78),
//	LineLastX(33),
	
	private int value;
	
	KlientCoordinates(int value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}
	
}
