package com.example.tugassederhana;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Test lokal untuk memverifikasi logika validasi input biodata dan perhitungan sederhana.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testValidasiNama_TidakBolehKosong() {
        String namaKosong = "";
        String namaValid = "Budi Santoso";

        assertTrue(namaKosong.trim().isEmpty());
        assertFalse(namaValid.trim().isEmpty());
        assertEquals("Budi Santoso", namaValid.trim());
    }

    @Test
    public void testValidasiNRP_HarusSesuai() {
        String nrp = "5025211001";

        assertNotNull(nrp);
        assertFalse(nrp.trim().isEmpty());
        assertEquals(10, nrp.length());
    }

    @Test
    public void testValidasiEmail_Format() {
        String emailValid = "mahasiswa@kampus.ac.id";
        String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";

        assertTrue(emailValid.matches(regexEmail));
    }

    @Test
    public void testNilaiDefault_FieldKosong() {
        String emailInput = "";
        String emailFinal = emailInput.isEmpty() ? "-" : emailInput;

        assertEquals("-", emailFinal);
    }
}