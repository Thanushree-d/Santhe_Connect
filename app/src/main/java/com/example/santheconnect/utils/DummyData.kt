package com.example.santheconnect.utils

import com.example.santheconnect.data.model.Location

object DummyData {

    val locations = listOf(

        Location(
            name = "Anekal Santhe",
            description = "Traditional rural bazaar famous for fresh greens and pottery.",
            specialty = "Organic Ragi",
            day = "Monday",
            lat = 12.7110,
            lng = 77.6963,
            type = "market",
            imageUrl = "https://picsum.photos/seed/anekal/400/300",
            address = "Anekal Town, Bengaluru Urban",
            distance = "12.5 km"
        ),

        Location(
            name = "KR Market",
            description = "One of Asia's largest flower markets.",
            specialty = "Fresh Flowers",
            day = "Daily",
            lat = 12.9611,
            lng = 77.5800,
            type = "market",
            imageUrl = "https://picsum.photos/400/300?random=4",
            address = "Kalasipalyam, Bengaluru",
            distance = "2 km"
        ),

        Location(
            name = "Devaraja Market",
            description = "Historic market in Mysuru.",
            specialty = "Mysore Silk",
            day = "Daily",
            lat = 12.3117,
            lng = 76.6528,
            type = "market",
            imageUrl = "https://picsum.photos/400/300?random=2",
            address = "Mysuru",
            distance = "140 km"
        ),

        Location(
            name = "Mandya Santhe",
            description = "Traditional jaggery market.",
            specialty = "Maddur Vada",
            day = "Friday",
            lat = 12.5230,
            lng = 76.8953,
            type = "food",
            imageUrl = "https://picsum.photos/400/300?random=3",
            address = "Mandya",
            distance = "95 km"
        ),

        Location(
            name = "Tumkur Santhe",
            description = "Large weekly livestock market.",
            specialty = "Coconut Oil",
            day = "Tuesday",
            lat = 13.3409,
            lng = 77.1010,
            type = "market",
            imageUrl = "https://picsum.photos/seed/tumkur/400/300",
            address = "Tumkur",
            distance = "70 km"
        ),

        Location(
            name = "Hassan Farmers Market",
            description = "Coffee and spice trading center.",
            specialty = "Coffee Beans",
            day = "Wednesday",
            lat = 13.0072,
            lng = 76.0960,
            type = "market",
            imageUrl = "https://picsum.photos/400/300?random=5",
            address = "Hassan",
            distance = "180 km"
        ),

        Location(
            name = "Shivamogga Santhe",
            description = "Famous Malnad produce market.",
            specialty = "Areca Nut",
            day = "Sunday",
            lat = 13.9299,
            lng = 75.5681,
            type = "market",
            imageUrl = "https://picsum.photos/seed/shivamogga/400/300",
            address = "Shivamogga",
            distance = "280 km"
        ),

        Location(
            name = "Udupi Market",
            description = "Temple town food market.",
            specialty = "Udupi Spices",
            day = "Daily",
            lat = 13.3409,
            lng = 74.7421,
            type = "food",
            imageUrl = "https://picsum.photos/seed/udupi/400/300",
            address = "Udupi",
            distance = "400 km"
        ),

        Location(
            name = "Mangalore Market",
            description = "Busy coastal fish and cashew market.",
            specialty = "Cashew Nuts",
            day = "Sunday",
            lat = 12.9141,
            lng = 74.8560,
            type = "food",
            imageUrl = "https://picsum.photos/seed/mangalore/400/300",
            address = "Mangaluru",
            distance = "350 km"
        ),

        Location(
            name = "Channapatna Santhe",
            description = "Famous toy-making town market.",
            specialty = "Wooden Toys",
            day = "Wednesday",
            lat = 12.6510,
            lng = 77.2067,
            type = "craft",
            imageUrl = "https://picsum.photos/seed/channapatna/400/300",
            address = "Channapatna",
            distance = "60 km"
        ),

        Location(
            name = "Doddaballapur Santhe",
            description = "Historic weaving market.",
            specialty = "Silk Sarees",
            day = "Tuesday",
            lat = 13.2945,
            lng = 77.5378,
            type = "craft",
            imageUrl = "https://picsum.photos/seed/doddaballapur/400/300",
            address = "Doddaballapur",
            distance = "40 km"
        ),

        Location(
            name = "Malavalli Santhe",
            description = "Traditional weekly fair.",
            specialty = "Dry Fish",
            day = "Thursday",
            lat = 12.3853,
            lng = 77.0545,
            type = "market",
            imageUrl = "https://picsum.photos/400/300?random=1",
            address = "Malavalli",
            distance = "100 km"
        ),

        Location(
            name = "Haveri Santhe",
            description = "North Karnataka chilli market.",
            specialty = "Byadagi Chilli",
            day = "Tuesday",
            lat = 14.7958,
            lng = 75.4024,
            type = "market",
            imageUrl = "https://picsum.photos/seed/haveri/400/300",
            address = "Haveri",
            distance = "340 km"
        ),

        Location(
            name = "Sirsi Santhe",
            description = "Forest honey and spice market.",
            specialty = "Wild Honey",
            day = "Friday",
            lat = 14.6195,
            lng = 74.8441,
            type = "market",
            imageUrl = "https://picsum.photos/seed/sirsi/400/300",
            address = "Sirsi",
            distance = "400 km"
        ),

        Location(
            name = "Kolar Santhe",
            description = "Tomato and vegetable market.",
            specialty = "Tomatoes",
            day = "Thursday",
            lat = 13.1350,
            lng = 78.1290,
            type = "market",
            imageUrl = "https://picsum.photos/400/300?random=6",
            address = "Kolar",
            distance = "70 km"
        ),

        // EXTRA SANTHES

        Location("Madikeri Santhe","Coffee hill market.","Coorg Coffee","Saturday",12.4244,75.7382,"market","https://picsum.photos/seed/madikeri/400/300","Madikeri","260 km"),
        Location("Belur Santhe","Temple handicraft market.","Stone Crafts","Monday",13.1631,75.8650,"craft","https://picsum.photos/seed/belur/400/300","Belur","210 km"),
        Location("Sakleshpur Santhe","Pepper and coffee market.","Pepper","Friday",12.9417,75.7842,"market","https://picsum.photos/seed/sakleshpur/400/300","Sakleshpur","240 km"),
        Location("Chikmagalur Santhe","Coffee trading market.","Arabica Coffee","Wednesday",13.3153,75.7754,"market","https://picsum.photos/seed/chikmagalur/400/300","Chikmagalur","250 km"),
        Location("Raichur Santhe","Rice and pulses market.","Rice","Sunday",16.2120,77.3439,"market","https://picsum.photos/seed/raichur/400/300","Raichur","410 km"),
        Location("Bidar Santhe","Historic Bidri craft market.","Bidriware","Tuesday",17.9133,77.5301,"craft","https://picsum.photos/seed/bidar/400/300","Bidar","690 km"),
        Location("Bagalkot Santhe","Traditional pottery market.","Clay Pots","Thursday",16.1817,75.6961,"craft","https://picsum.photos/seed/bagalkot/400/300","Bagalkot","520 km"),
        Location("Karwar Market","Coastal seafood market.","Sea Fish","Sunday",14.8136,74.1297,"food","https://picsum.photos/seed/karwar/400/300","Karwar","510 km"),
        Location("Vijayapura Santhe","Dry fruits trading market.","Dry Fruits","Friday",16.8302,75.7100,"market","https://picsum.photos/seed/vijayapura/400/300","Vijayapura","560 km"),
        Location("Bellary Santhe","Mining town market.","Iron Tools","Monday",15.1394,76.9214,"market","https://picsum.photos/seed/bellary/400/300","Ballari","340 km"),

        Location("Davanagere Santhe","Butter dosa market.","Benne Dosa","Tuesday",14.4644,75.9218,"food","https://picsum.photos/seed/davanagere/400/300","Davanagere","300 km"),
        Location("Chitradurga Santhe","Historic fort town market.","Groundnuts","Wednesday",14.2306,76.3980,"market","https://picsum.photos/seed/chitradurga/400/300","Chitradurga","320 km"),
        Location("Hubli Market","Commercial city market.","Cotton","Daily",15.3647,75.1240,"market","https://picsum.photos/seed/hubli/400/300","Hubli","410 km"),
        Location("Belagavi Santhe","Border trade market.","Kunda Sweet","Saturday",15.8497,74.4977,"food","https://picsum.photos/seed/belagavi/400/300","Belagavi","500 km"),
        Location("Gadag Santhe","Traditional grain market.","Jowar","Thursday",15.4315,75.6350,"market","https://picsum.photos/seed/gadag/400/300","Gadag","430 km"),

        Location("Yadgir Santhe","Farmers trading market.","Red Gram","Monday",16.7704,77.1376,"market","https://picsum.photos/seed/yadgir/400/300","Yadgir","520 km"),
        Location("Ramanagara Santhe","Silk cocoon market.","Silk Cocoon","Friday",12.7219,77.2810,"market","https://picsum.photos/seed/ramanagara/400/300","Ramanagara","55 km"),
        Location("Srirangapatna Market","Historic town market.","Incense","Sunday",12.4226,76.6937,"craft","https://picsum.photos/seed/srirangapatna/400/300","Mandya","130 km"),
        Location("Nanjangud Santhe","Temple market.","Bananas","Tuesday",12.1191,76.6834,"market","https://picsum.photos/seed/nanjangud/400/300","Nanjangud","160 km"),
        Location("Kollegal Santhe","Forest produce market.","Sandalwood Crafts","Thursday",12.1547,77.1100,"craft","https://picsum.photos/seed/kollegal/400/300","Kollegal","180 km"),

        Location("Bhatkal Market","Coastal spice market.","Seafood","Saturday",13.9853,74.5553,"food","https://picsum.photos/seed/bhatkal/400/300","Bhatkal","430 km"),
        Location("Honnavar Santhe","Fishing harbor market.","Dry Fish","Monday",14.2798,74.4442,"food","https://picsum.photos/seed/honnavar/400/300","Honnavar","440 km"),
        Location("Kundapura Santhe","Coastal farming market.","Coconuts","Friday",13.6313,74.6900,"market","https://picsum.photos/seed/kundapura/400/300","Kundapura","420 km"),
        Location("Puttur Market","Famous areca nut market.","Areca Nut","Wednesday",12.7597,75.2017,"market","https://picsum.photos/seed/puttur/400/300","Puttur","360 km"),
        Location("Moodbidri Santhe","Traditional Jain town market.","Spices","Sunday",13.0087,74.9952,"market","https://picsum.photos/seed/moodbidri/400/300","Moodbidri","355 km"),

        Location("Ranebennur Santhe","Cotton and grain market.","Cotton","Tuesday",14.6220,75.6296,"market","https://picsum.photos/seed/ranebennur/400/300","Ranebennur","330 km"),
        Location("Harihar Market","Industrial town market.","Steel Utensils","Friday",14.5129,75.8072,"craft","https://picsum.photos/seed/harihar/400/300","Harihar","310 km"),
        Location("Tiptur Santhe","Coconut trading hub.","Coconut","Monday",13.2563,76.4774,"market","https://picsum.photos/seed/tiptur/400/300","Tiptur","190 km"),
        Location("Arsikere Market","Agriculture produce market.","Pulses","Thursday",13.3133,76.2570,"market","https://picsum.photos/seed/arsikere/400/300","Arsikere","210 km"),
        Location("Bhadravati Santhe","Steel city market.","Steel Goods","Sunday",13.8485,75.7050,"market","https://picsum.photos/seed/bhadravati/400/300","Bhadravati","290 km"),

        Location("Kushalnagar Santhe","Coorg produce market.","Pepper","Saturday",12.4570,75.9590,"market","https://picsum.photos/seed/kushalnagar/400/300","Kodagu","250 km"),
        Location("Virajpet Market","Coffee bean trading.","Coffee","Friday",12.1964,75.8057,"market","https://picsum.photos/seed/virajpet/400/300","Virajpet","280 km"),
        Location("Gonikoppal Santhe","Malnad village market.","Cardamom","Wednesday",12.1825,75.9453,"market","https://picsum.photos/seed/gonikoppal/400/300","Kodagu","285 km"),
        Location("Alur Santhe","Village farming market.","Vegetables","Monday",13.0392,75.9872,"market","https://picsum.photos/seed/alur/400/300","Alur","200 km"),
        Location("Holenarasipura Market","Traditional rice market.","Rice","Tuesday",12.7855,76.2430,"market","https://picsum.photos/seed/holenarasipura/400/300","Hassan","195 km")
    )
}