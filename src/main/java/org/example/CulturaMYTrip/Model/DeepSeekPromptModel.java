package org.example.CulturaMYTrip.Model;

public class DeepSeekPromptModel {


//    public String getTravelPlanprompt(String content) {
//        return "You are a professional Malaysian cultural tourism AI assistant. Based on the following input cities or regions provided by the user, generate a multi-day cultural travel plan in Malaysia.\n\n"
//                + "Cities or regions to include: " + content + "\n\n"
//                + "Each day must include:\n"
//                + "- Day number and title (e.g., 'Day 1: Explore Kuala Lumpur Heritage')\n"
//                + "- Multiple culturally significant locations (2–4), each with:\n"
//                + "   → Name: [Place name, e.g., 'Istana Negara']\n"
//                + "   → Description: [Cultural or historical background, significance, stories, etc.]\n\n"
//                + "Use the **exact format** below for every day:\n"
//                + "Day [X]: [Day Title]\n"
//                + "→ Name: [Place 1 Name]\n"
//                + "→ Description: [Place 1 Cultural or Historical Background]\n"
//                + "→ Name: [Place 2 Name]\n"
//                + "→ Description: [Place 2 Cultural or Historical Background]\n"
//                + "[... add more if applicable]\n\n"
//                + "Each day's entry must follow this format and be separated by a blank line.\n"
//                + "If the provided location(s) do not yield valid cultural content, return exactly: \"insufficient data\".\n"
//                + "The response must be clean, concise, and in English.";
//    }

    public String getTravelPlanprompt(String content, String requirement, String budget, String days, String departure, String cultureType) {
        return "You are a professional Malaysian cultural tourism AI assistant. Based on the following user inputs, generate a meaningful and engaging multi-day cultural travel plan in Malaysia.\n\n"
                + "Cities or regions to include: " + content + "\n"
                + "User departure point: " + departure + "\n"
                + "Number of travel days: " + days + "\n"
                + "Budget range: " + budget + "\n"
                + "User requirements or preferences: " + requirement + "\n"
                + "Cultural theme or interest: " + cultureType + "\n\n"
                + "Each day must include:\n"
                + "- Day number and title (e.g., 'Day 1: Explore Kuala Lumpur Heritage')\n"
                + "- Multiple culturally significant locations (2–4), each with:\n"
                + "   → Name: [Place name, e.g., 'Istana Negara']\n"
                + "   → Description: [Cultural or historical background, significance, stories, etc.]\n\n"
                + "Use the **exact format** below for every day:\n"
                + "Day [X]: [Day Title]\n"
                + "→ Name: [Place 1 Name]\n"
                + "→ Description: [Place 1 Cultural or Historical Background]\n"
                + "[...]\n\n"
                + "Each day's entry must follow this format and be separated by a blank line.\n"
                + "If the provided location(s) do not yield valid cultural content, return exactly: \"insufficient data\".";
    }



//    public String getAttractionprompt(String content) {
//        return "You are a Malaysian tourism assistant AI. Based on the following Malaysian cities or regions provided by the user, return a list of famous cultural or historical attractions in that area.\n\n"
//                + "Cities or regions: " + content + "\n\n"
//                + "Return only:\n"
//                + "Famous Attractions:\n"
//                + "- [Name 1]: [Short description of its cultural or historical significance]\n"
//                + "- [Name 2]: [...]\n"
//                + "- [Name 3]: [...]\n\n"
//                + "Use this exact format. If nothing is relevant, return: \"insufficient data\".";
//    }

    public String getAttractionprompt(String travelplan, String requirement, String budget, String days, String departure,String cultureType) {
        return "You are a Malaysian tourism assistant AI. Based on the following cities or regions, return a list of famous cultural or historical attractions.\n\n"
                + "Travelplans: " + travelplan + "\n\n"
                + "Cultural theme or interest: " + cultureType + "\n\n"
                + "Departure: " + departure + " | Days: " + days + " | Budget: " + budget + "\n"
                + "User requirements: " + requirement + "\n\n"
                + "Return only:\n"
                + "Famous Attractions:\n"
                + "- [Name 1]: [Short description of its cultural or historical significance]\n"
                + "- [...]\n"
                + "Use this exact format. If nothing is relevant, return: \"insufficient data\".";
    }


//    public String getSouvenirprompt() {
//
//        return "You are a Malaysian cultural tourism AI assistant. Based on the uploaded image or location, suggest a list of culturally meaningful souvenirs that tourists can bring home.\n\n"
//                + "Each souvenir should be:\n"
//                + "- Specific to Malaysian culture (e.g., Batik, Songket, Wau, Gamat balm)\n"
//                + "- Paired with a short description explaining its origin, use, or significance\n\n"
//                + "Use **this exact format**:\n"
//                + "Souvenir:\n"
//                + "- [Souvenir Name 1]: [Short description of what it is and why it is culturally important]\n"
//                + "- [Souvenir Name 2]: [...]\n"
//                + "- [...]\n\n"
//                + "If no relevant souvenir can be identified, return: \"insufficient data\".";
//    }

    public String getSouvenirprompt(String travelplan,String requirement, String budget, String days, String departure,String cultureType) {
        return "You are a Malaysian cultural tourism AI assistant. Based on the uploaded image, location, and user preferences, suggest culturally meaningful souvenirs.\n\n"
                + "User departure: " + departure + " | Days: " + days + " | Budget: " + budget + "\n"
                + "User requirements: " + requirement + "\n\n"
                + "Travelplans: " + travelplan + "\n\n"
                + "Cultural theme or interest: " + cultureType + "\n\n"
                + "Format:\n"
                + "Souvenir:\n"
                + "- [Souvenir Name]: [Short description of what it is and why it is culturally important]\n"
                + "[...]\n"
                + "If no relevant souvenir can be identified, return: \"insufficient data\".";
    }

//    public String getFoodprompt() {
//
//        return "You are a Malaysian cultural food assistant AI. Based on the user’s image or location, recommend popular local dishes from different Malaysian regions.\n\n"
//                + "Use this format:\n"
//                + "Category: [Region or State, e.g., Penang, Kelantan, Sabah]\n"
//                + "→ Food: [Name of the dish]\n"
//                + "→ Popularity: [High, Medium, Low]\n"
//                + "→ Description: [Short cultural or historical background about the food]\n\n"
//                + "List 2–5 entries. Separate each entry by a blank line.";
//    }

    public String getFoodprompt(String travelplan,String requirement, String budget, String days, String departure,String cultureType) {
        return "You are a Malaysian cultural food assistant AI. Based on the image, location, and user preferences, recommend local dishes from different regions.\n\n"
                + "Departure: " + departure + " | Travel days: " + days + " | Budget: " + budget + "\n"
                + "Requirement: " + requirement + "\n\n"
                + "Travelplans: " + travelplan + "\n\n"
                + "Cultural theme or interest: " + cultureType + "\n\n"
                + "Format:\n"
                + "Category: [Region]\n"
                + "→ Food: [Dish Name]\n"
                + "→ Popularity: [High, Medium, Low]\n"
                + "→ Description: [Cultural background]\n"
                + "[...up to 5 entries]\n"
                + "Separate each entry by a blank line.";
    }


//    public String getHotelprompt() {
//
//        return "You are a Malaysian tourism assistant AI. Based on the user's location or uploaded image, suggest culturally significant or convenient hotels in different regions.\n\n"
//                + "Use the following exact format for each hotel:\n"
//                + "Category: [Region or Area, e.g., Penang, Melaka, Kota Kinabalu]\n"
//                + "→ Hotel: [Hotel name]\n"
//                + "→ Rating: [Star level or High/Medium/Low]\n"
//                + "→ Description: [Brief cultural, historical, or practical info about the hotel]\n\n"
//                + "List 2–5 hotels and separate entries with a blank line.";
//
//    }

    public String getHotelprompt(String travelplan,String requirement, String budget, String days, String departure) {
        return "You are a Malaysian tourism assistant AI. Based on location/image and user preferences, recommend culturally relevant hotels.\n\n"
                + "Departure: " + departure + " | Travel Days: " + days + " | Budget: " + budget + "\n"
                + "User requirements: " + requirement + "\n\n"
                + "Travelplans: " + travelplan + "\n\n"
                + "Format:\n"
                + "Category: [Region or Area]\n"
                + "→ Hotel: [Name]\n"
                + "→ Rating: [Star level or popularity]\n"
                + "→ Description: [Cultural/historical/practical info]\n"
                + "If no valid hotel is found, return exactly: \"insufficient data\".";
    }

    public String getCultureprompt(String cultureType) {

        return "You are a Malaysian cultural heritage AI expert. Based on the cultural input provided below, write a clear and engaging explanation for curious tourists to understand it.\n\n"
                + "Cultural theme or interest: " + cultureType + "\n\n"
                + "Your explanation must answer:\n"
                + "- What it is (definition)\n"
                + "- Where it comes from (ethnic group, region, history, or context)\n"
                + "- Why it matters culturally (symbolism, role in society, historical meaning, etc.)\n\n"
                + "Tone: Friendly, informative, and tourist-friendly (like a local guide).\n"
                + "Length: One paragraph (max 150 words).\n"
                + "If the input is not a recognized cultural item, tradition, group, or concept, return exactly: \"insufficient data\".";
    }









    public String getBudgetprompt(String travelPlan, String requirement, String budget, String days, String departure) {
        return "You are a professional Malaysian travel assistant system. Based on the provided travel plan and user details, estimate a suitable **total travel cost range** in US Dollars (USD) for the user.\n\n"
                + "Travel Plan:\n" + travelPlan + "\n\n"
                + "User Inputs:\n"
                + "- Departure Point: " + departure + "\n"
                + "- Travel Days: " + days + "\n"
                + "- User Budget Preference: " + budget + "\n"
                + "- Special Requirements: " + requirement + "\n\n"
                + "Your cost estimation must be realistic and follow current Malaysian travel market prices, converted to USD. Consider:\n"
                + "- Type and number of locations\n"
                + "- Length of trip\n"
                + "- Typical transport, accommodation, food, and activity costs\n\n"
                + "You must always provide both:\n"
                + "1. Estimated Cost: USD xxx–xxx (e.g., USD 250–400)\n"
                + "2. Explanation: A brief justification (e.g., includes intercity travel, mid-range hotel, local food, entrance fees)\n\n"
                + "Example output:\n"
                + "Estimated Cost: USD 300–450\n"
                + "Explanation: Based on 3-day trip to Melaka and Kuala Lumpur, including hotel stay, Grab transport, and attraction tickets.\n\n"
                + "If no valid travel plan is provided, return exactly: \"insufficient data\".\n"
                + "The response must be in English, clean, and without extra comments.";
    }

    public String getCityExtractionPrompt(String travelPlanText) {
        return "You are a Malaysian cultural tourism AI expert.\n\n"
                + "Based on the following cultural travel plan, extract all **distinct Malaysian cities or regions** mentioned (do not include individual attractions or landmarks), and for each one, provide a concise cultural or historical explanation.\n\n"
                + "Cultural Travel Plan:\n"
                + travelPlanText + "\n\n"
                + "Return the result strictly in this format:\n"
                + "Category: [City or Region]\n"
                + "→ Description: [Short explanation of its cultural or historical significance. Include local traditions, ethnic background, or interesting facts in under 80 words.]\n"
                + "[repeat this format for all cities or regions]\n\n"
                + "⚠️ Do not list attractions. Focus only on cities or regions.\n"
                + "If no valid cities or regions are found, return exactly: \"insufficient data\".";
    }














}
