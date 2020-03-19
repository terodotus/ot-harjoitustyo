
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {
    Maksukortti kortti;
    Maksukortti kortti2;
    Maksukortti kortti3;
    
    public MaksukorttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
        kortti2=new Maksukortti(2.5);
        kortti3=new Maksukortti(4);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {}
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        Maksukortti kortti = new Maksukortti(10);

        String vastaus = kortti.toString();

        assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        Maksukortti kortti = new Maksukortti(10);

        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        Maksukortti kortti = new Maksukortti(10);

        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        Maksukortti kortti = new Maksukortti(10);

        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 2
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        Maksukortti kortti = new Maksukortti(10);

        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 2
        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivisenSummanlataaminenEiMuutaArvoa() {
        kortti.lataaRahaa(-200);
        kortti.lataaRahaa(-2);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void voiOstaaEdullisenLounaanKunVainSenVerranArvoa() {
        kortti2.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti2.toString());
    }
    
    @Test
    public void voiOstaaMaukkaanLounaanKunVainSenVerranArvoa() {
        kortti3.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti3.toString());
    }
    
}