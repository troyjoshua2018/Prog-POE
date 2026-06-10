/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe_part3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author troyj
 */

public class POE_Part3 {
    public static void main(String[] args) {
        RegistrationClass registrationModule = new RegistrationClass();
        LoginClass loginModule = new LoginClass();

        // 1. Process Registration Flow
        UserClass newUser = registrationModule.registerUser();

        // 2. Process Login Flow & Execute Program Engine on Success
        if (loginModule.authenticate(newUser)) {
            QuickChat app = new QuickChat();
            app.start(newUser.getUsername(), newUser.getPhoneNumber());
        }
    }
}