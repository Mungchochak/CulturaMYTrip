package org.example.CulturaMYTrip.Model;

public class DeepSeekPromptModel {



    public String getTravelPlanprompt(String requirement, String budget, String days, String departure, String cultureType) {
        return "You are a professional Malaysian cultural tourism AI assistant. Based on the following user inputs, generate a meaningful and engaging multi-day cultural travel plan in Malaysia.\n\n"
                + "Cities or regions to include: All Malaysia cities, including iconic destinations like Kuala Lumpur, George Town, Melaka, Kota Bharu, Kuching, Kota Kinabalu, Langkawi, and Cameron Highlands\n"
                + "User departure point: " + departure + "\n"
                + "Number of travel days: " + days + "\n"
                + "Budget range: " + budget + "\n"
                + "User requirements or preferences: " + requirement + "\n"
                + "Cultural theme or interest: " + cultureType + "\n\n"
                +"It doesn't have to be all cultural attractions; there should also be famous Malaysian attractions like the Petronas Twin Towers"
                + "Each day must include:\n"
                + "- Day number and title (e.g., 'Day 1: Explore Kuala Lumpur Heritage')\n"
                + "- Multiple culturally significant locations (2–4), each with:\n"
                + "   → Name: [Place name, e.g., 'Istana Negara']\n"
                + "   → Description: [Cultural or historical background, significance, stories, etc.]\n\n"
                + "Use the exact format below for every day. Do not add asterisks or stylistic symbols:\n"
                + "Day [X]: [Day Title]\n"
                + "→ Name: [Place 1 Name]\n"
                + "→ Description: [Place 1 Cultural or Historical Background]\n"
                + "[...]\n\n"
                + "Each day's entry must follow this format and be separated by a blank line.\n"
                + "If the provided location(s) do not yield valid cultural content, return exactly: \"insufficient data\".";
    }







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



//    public String getSouvenirprompt(String travelplan, String requirement, String budget, String days, String departure, String cultureType) {
//        return "You are a Malaysian cultural tourism AI assistant. Based on the user's travel plan, preferences, and cultural interest, recommend **authentic and meaningful Malaysian souvenirs** that reflect cultural identity.\n\n"
//                + "User Travel Info:\n"
//                + "- Departure Point: " + departure + "\n"
//                + "- Number of Travel Days: " + days + "\n"
//                + "- Budget Preference: " + budget + " USD\n"
//                + "- User Requirements: " + requirement + "\n"
//                + "- Cultural Theme or Interest: " + cultureType + "\n"
//                + "- Travel Plan Summary: " + travelplan + "\n\n"
//                + "You must list suitable souvenir items that are:\n"
//                + "- Related to local heritage, arts, food, or traditional symbols\n"
//                + "- Reasonable in price and size (travel-friendly)\n"
//                + "- Linked to Malaysian culture (e.g., batik, wau, pewter items, local snacks)\n\n"
//                + "Strict output format:\n"
//                + "Souvenir:\n"
//                + "- [Souvenir Name]: [1-sentence description explaining cultural importance or origin]\n"
//                + "- [Souvenir Name]: [description]\n"
//                + "[...]\n\n"
//                + "If the travel information is too vague, return exactly: \"insufficient data\".";
//    }


//    public String getSouvenirprompt(String travelplan, String requirement, String budget, String days, String departure, String cultureType) {
//        return "You are a Malaysian cultural tourism AI assistant. Based on the user's travel plan, preferences, and cultural interest, recommend authentic and meaningful Malaysian souvenirs that reflect cultural identity.\n\n"
//                + "User Travel Info:\n"
//                + "Departure Point: " + departure + "\n"
//                + "Number of Travel Days: " + days + "\n"
//                + "Budget Preference: " + budget + " USD\n"
//                + "User Requirements: " + requirement + "\n"
//                + "Cultural Theme or Interest: " + cultureType + "\n"
//                + "Travel Plan Summary: " + travelplan + "\n\n"
//                + "You must list suitable souvenir items that are:\n"
//                + "Related to local heritage, arts, food, or traditional symbols\n"
//                + "Reasonable in price and size (travel-friendly)\n"
//                + "Linked to Malaysian culture (e.g., batik, wau, pewter items, local snacks)\n\n"
//                + "Strict output format:\n"
//                + "Souvenir:\n"
//                + "[Souvenir Name]: [1-sentence description explaining cultural importance or origin]\n"
//                + "[Souvenir Name]: [description]\n"
//                + "[...]\n\n"
//                + "If the travel information is too vague, return exactly: \"insufficient data\".";
//    }

//    public String getSouvenirprompt(String travelplan, String requirement, String budget, String days, String departure, String cultureType) {
//        return "You are a Malaysian cultural tourism AI assistant. Based on the user's travel plan, preferences, and cultural interests, recommend authentic and meaningful Malaysian souvenirs that reflect cultural identity.\n\n"
//                + "User Travel Information:\n"
//                + "Departure Point: " + departure + "\n"
//                + "Number of Travel Days: " + days + "\n"
//                + "Budget: " + budget + " USD\n"
//                + "Special Requirements: " + requirement + "\n"
//                + "Cultural Theme or Interest: " + cultureType + "\n"
//                + "Travel Plan Summary: " + travelplan + "\n\n"
//                + "Your recommendations must:\n"
//                + "- Match the cities and regions in the user's travel route\n"
//                + "- Be culturally tied to specific locations (e.g., recommend Kek Lapis only if visiting Sarawak)\n"
//                + "- Be suitable for the given budget and number of days (avoid luxury or bulky items if budget or time is limited)\n"
//                + "- Be compact, practical, and easy to carry while traveling\n"
//                + "- Reflect Malaysian cultural identity: traditional arts, crafts, foods, symbols, attire, or local materials\n"
//                + "- Consider the user's background or preferences based on their departure point and selected cultural theme\n\n"
//                + "Strict output format:\n"
//                + "Souvenir:\n"
//                + "- [Souvenir Name]: [One-sentence description explaining its cultural relevance and why it suits the user's trip and budget]\n"
//                + "- [Souvenir Name]: [description]\n"
//                + "[...]\n\n"
//                + "Avoid generic or empty recommendations. If the travel information is truly insufficient, return exactly: \"insufficient data\".";
//    }

    public String getSouvenirprompt(String travelplan, String requirement, String budget, String days, String departure, String cultureType) {
        return "You are a Malaysian cultural tourism AI assistant. Based on the user's travel plan, preferences, and cultural interests, recommend authentic and meaningful Malaysian souvenirs that reflect cultural identity.\n\n"
                + "User Travel Information:\n"
                + "Departure Point: " + departure + "\n"
                + "Number of Travel Days: " + days + "\n"
                + "Budget: " + budget + " USD\n"
                + "Special Requirements: " + requirement + "\n"
                + "Cultural Theme or Interest: " + cultureType + "\n"
                + "Travel Plan Summary: " + travelplan + "\n\n"
                + "Your recommendations must:\n"
                + "- Match the cities and regions in the user's travel route\n"
                + "- Be culturally tied to specific locations (e.g., recommend Kek Lapis only if visiting Sarawak)\n"
                + "- Be suitable for the given budget and number of days (avoid luxury or bulky items if budget or time is limited)\n"
                + "- Be compact, practical, and easy to carry while traveling\n"
                + "- Reflect Malaysian cultural identity: traditional arts, crafts, foods, symbols, attire, or local materials\n"
                + "- Consider the user's background or preferences based on their departure point and selected cultural theme\n\n"
                + "Strict output format:\n"
                + "Souvenir:\n"
                + "- [Souvenir Name]: [One-sentence description explaining its cultural relevance and why it suits the user's trip and budget]\n"
                + "- [Souvenir Name]: [description]\n"
                + "[...]\n\n"
                + "Do not use bold symbols (**) or any Markdown formatting. Output should be in plain text only.\n"
                + "Avoid generic or empty recommendations. If the travel information is truly insufficient, return exactly: \"insufficient data\".";
    }


    public String getFoodprompt(String travelplan, String requirement, String budget, String days, String departure, String cultureType) {
        return "You are a Malaysian cultural food assistant AI. Based on the image, location, and user preferences, recommend local dishes from different regions.\n\n"
                + "Departure: " + departure + " | Travel days: " + days + " | Budget: " + budget + "\n"
                + "Requirement: " + requirement + "\n\n"
                + "Travelplans: " + travelplan + "\n\n"
                + "Cultural theme or interest: " + cultureType + "\n\n"
                + "Please make sure the first entry is complete and valid. Do not generate any empty regions or placeholders.\n\n"
                + "Format:\n"
                + "Category: [Region]\n"
                + "→ Food: [Dish Name]\n"
                + "→ Popularity: [High, Medium, Low]\n"
                + "→ Description: [Cultural background]\n"
                + "[...up to 5 entries]\n"
                + "Separate each entry by a blank line.";
    }




    public String getHotelprompt(String travelplan, String requirement, String budget, String days, String departure) {
        return "You are a Malaysian tourism assistant AI. Based on the user's travel plan and preferences, recommend culturally relevant and practical hotels.\n\n"
                + "User Info:\n"
                + "- Departure: " + departure + "\n"
                + "- Travel Days: " + days + "\n"
                + "- Budget: " + budget + " USD\n"
                + "- Special Requirements: " + requirement + "\n"
                + "- Travel Locations: " + travelplan + "\n\n"
                + "Guidelines:\n"
                + "- Recommend 3 to 6 hotel options across different areas.\n"
                + "- Hotel name and area must be valid — do not use empty names, brackets, or placeholders like () or ---.\n"
                + "- Include cultural value, budget suitability, or travel convenience in the description.\n"
                + "- Do NOT include markdown formatting or bold (**text**). Keep it plain and clean.\n\n"
                + "Strict Output Format:\n"
                + "Category: [Area Name]\n"
                + "→ Hotel: [Hotel Name]\n"
                + "→ Rating: [3/5, 4/5 etc. only star rating]\n"
                + "→ Description: [Brief explanation including cultural relevance, affordability, or travel convenience]\n\n"
                + "If no suitable hotel is found, return exactly: \"insufficient data\".";
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
        return "You are a professional Malaysian travel assistant system. Based on the provided travel plan and user details, estimate a realistic travel cost breakdown in US Dollars (USD).\n\n"
                + "Travel Plan:\n" + travelPlan + "\n\n"
                + "User Inputs:\n"
                + "- Departure Point: " + departure + "\n"
                + "- Travel Days: " + days + "\n"
                + "- User Maximum Budget: " + budget + " USD\n"
                + "- Special Requirements: " + requirement + "\n\n"
                + "IMPORTANT:\n"
                + "- The total estimated cost (Food + Live + Transport) must not exceed the user's maximum budget.\n"
                + "- Provide a detailed justification for each cost item using → after the USD amount.\n"
                + "- Each explanation must clearly state what the cost covers, why that amount is reasonable, and how it relates to the travel plan (e.g., number of meals, type of hotels, distance between cities).\n"
                + "- Use this exact format:\n\n"
                + "Food: USD [amount] → [Detailed explanation about food cost]\n"
                + "Live: USD [amount] → [Detailed explanation about accommodation cost]\n"
                + "Transport: USD [amount] → [Detailed explanation about transport cost]\n"
                + "Total: USD [sum of above]\n\n"
                + "Example:\n"
                + "Food: USD 80 → Covers 3 meals per day for 4 days at local eateries and street food stalls, averaging USD 6.7 per meal\n"
                + "Live: USD 150 → Budget accommodation at centrally located 3-star hotels in KL and Melaka, around USD 38 per night\n"
                + "Transport: USD 70 → Includes Grab rides within KL, intercity bus from KL to Melaka, and return trip\n"
                + "Total: USD 300\n\n"
                + "If no valid travel plan is provided, return exactly: \"insufficient data\".\n"
                + "Do not include any extra comments. Format must be clean and strictly follow the above.";
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
