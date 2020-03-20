
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa=new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luotuKassaOlemassa() {
        assertTrue(kassa!=null);      
    }
    
    @Test
    public void luodussaKassassaOikeaSaldo() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void luodussaKassassaOikeaMaaraEdullisiaLounaita() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luodussaKassassaOikeaMaaraMaukkaitaLounaita() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimii() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiPalauttaaOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiLisaaMyytyjaEdullisia() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEiOnnistuJosLiianVahanRahaa() {
        assertEquals(5, kassa.syoEdullisesti(5));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiToimii() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiPalauttaaOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiLisaaMyytyjaMaukkaita() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiOnnistuJosLiianVahanRahaa() {
        assertEquals(5, kassa.syoMaukkaasti(5));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaToimii() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaPalauttaaOikein() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaLisaaMyytyjaMaukkaita() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaEiOnnistuJosLiianVahanRahaa() {
        kortti.otaRahaa(955);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKortillaToimii() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaPalauttaaOikein() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaLisaaMyytyjaMaukkaita() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKortillaEiOnnistuJosLiianVahanRahaa() {
        kortti.otaRahaa(955);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void lataaRahaaToimiiJosNegatiivinen() {
        kortti.lataaRahaa(-5);
        assertEquals(995,kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaToimiiJosPositiivinen() {
        kortti.lataaRahaa(5);
        assertEquals(1005,kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortillaToimiiJosNegatiivinen() {
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(1000,kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortillaToimiiJosPositiivinen() {
        kassa.lataaRahaaKortille(kortti, 5);
        assertEquals(1005,kortti.saldo());
        assertEquals(100005, kassa.kassassaRahaa());
    }
    
}
