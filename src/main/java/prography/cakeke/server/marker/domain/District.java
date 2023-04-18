package prography.cakeke.server.marker.domain;

public enum District {
    JONGNO(new Point(37.636302, 126.978045), new Point(37.559102, 126.965186), new Point(37.582371, 127.017418), new Point(37.619602, 127.015573)),
    JUNG(new Point(37.615102, 126.970870), new Point(37.555288, 126.996286), new Point(37.563152, 126.997145), new Point(37.617400, 127.010827)),
    YONGSAN(new Point(37.564247, 126.968779), new Point(37.532039, 126.973013), new Point(37.541407, 126.992049), new Point(37.551586, 126.996855)),
    SEONGDONG(new Point(37.573642, 127.040785), new Point(37.551475, 127.036910), new Point(37.545425, 127.049428), new Point(37.571126, 127.056351)),
    GWANGJIN(new Point(37.554149, 127.086816), new Point(37.545674, 127.088810), new Point(37.538555, 127.076025), new Point(37.553405, 127.071159)),
    DONGDAEMUN(new Point(37.601729, 127.078635), new Point(37.569104, 127.068962), new Point(37.570065, 127.086301), new Point(37.595410, 127.094216)),
    JUNGNANG(new Point(37.606610, 127.080986), new Point(37.590135, 127.089598), new Point(37.596132, 127.112484), new Point(37.610717, 127.113689)),
    SEONGBUK(new Point(37.623923, 127.019736), new Point(37.606388, 127.018705), new Point(37.602832, 127.037623), new Point(37.622345, 127.046131)),
    GANGBUK(new Point(37.677488, 127.028760), new Point(37.648303, 127.025687), new Point(37.648911, 127.050937), new Point(37.670035, 127.058431)),
    DOBONG(new Point(37.695824, 127.045180), new Point(37.663932, 127.038151), new Point(37.661836, 127.056708), new Point(37.691166, 127.066009)),
    NOWON(new Point(37.669410, 127.107560), new Point(37.638504, 127.081474), new Point(37.654278, 127.063500), new Point(37.680853, 127.091556)),
    EUNPYEONG(new Point(37.618423, 126.945843), new Point(37.610481, 126.936448), new Point(37.635994, 126.927687), new Point(37.646373, 126.939310)),
    SEODAEMUN(new Point(37.596438, 126.935020), new Point(37.574285, 126.938661), new Point(37.582286, 126.955631), new Point(37.601497, 126.952235)),
    MAPO(new Point(37.583898, 126.910522), new Point(37.550275, 126.895547), new Point(37.556192, 126.931321), new Point(37.571260, 126.940313)),
    YANGCHEON(new Point(37.537077, 126.856136), new Point(37.512589, 126.841327), new Point(37.527666, 126.817514), new Point(37.547104, 126.832932)),
    GANGSEO(new Point(37.575358, 126.808673), new Point(37.534500, 126.816390), new Point(37.532423, 126.843505), new Point(37.566213, 126.851738)),
    GURO(new Point(37.508432, 126.858676), new Point(37.482067, 126.876187), new Point(37.496100, 126.893233), new Point(37.522700, 126.877779)),
    GUMCHEON(new Point(37.460097, 126.900155), new Point(37.467248, 126.902328), new Point(37.468139, 126.897475), new Point(37.461131, 126.896971)),
    YEONGDEUNGPO(new Point(37.534388, 126.912924), new Point(37.517191, 126.912855), new Point(37.516120, 126.935978), new Point(37.527966, 126.939782)),
    DONGJAK(new Point(37.510038, 126.945645), new Point(37.492665, 126.946036), new Point(37.493904, 126.964594), new Point(37.514312, 126.969494)),
    GWANAK(new Point(37.467345, 126.927791), new Point(37.462946, 126.948968), new Point(37.482190, 126.952664), new Point(37.485199, 126.930660)),
    SEOCHO(new Point(37.485143, 126.987410), new Point(37.476733, 126.981056), new Point(37.468344, 126.991319), new Point(37.475836, 127.001129)),
    GANGNAM(new Point(37.512825, 127.057657), new Point(37.485989, 127.044316), new Point(37.496807, 127.021507), new Point(37.526855, 127.034634)),
    SONGPA(new Point(37.504930, 127.069508), new Point(37.495786, 127.103557), new Point(37.523524, 127.119174), new Point(37.531641, 127.089325)),
    GANGDONG(new Point(37.557109, 127.145342), new Point(37.551463, 127.173620), new Point(37.528067, 127.177439), new Point(37.523276, 127.148485));

    private Point[] vertices;

    private District(Point... vertices) {
        this.vertices = vertices;
    }

    public Point[] getVertices() {
        return vertices;
    }
}

class Point {
    private double latitude;
    private double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}