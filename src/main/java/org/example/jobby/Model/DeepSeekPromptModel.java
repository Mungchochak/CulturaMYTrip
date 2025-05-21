package org.example.jobby.Model;

public class DeepSeekPromptModel {


    public String getSkillMatchingprompt() {

//        return "You are an expert resume analyzer. Please extract and summarize this person's core professional skills, technologies, and tools based on their resume. "
//                + "Focus only on technical abilities such as programming languages, frameworks, and databases. "
//                + "Respond with only skill keywords separated by the '|' symbol (e.g., Java | Spring | MySQL), in a single formal English line, strictly under 100 characters. "
//                + "Do not include any explanations or analysis.";

        return "You are an expert resume analyzer. Please extract and summarize this person's core professional skills, technologies, and tools based on their resume. "
                + "Focus only on technical abilities such as programming languages, frameworks, and databases. "
                + "Respond with only skill keywords separated by ' | ' (e.g., Java | Spring | MySQL), in a single formal English line, strictly under 100 characters. "
                + "Do not include any explanations or analysis.";
    }

    public String getWorkingExperienceprompt() {

        return "You are a professional resume analysis system. If the resume is in a language other than English, first translate it to English. "
                + "Then extract the candidate’s work experience and any project history. Summarize the following: companies and roles (with job titles), "
                + "total work experience in years, key projects (name/scope), individual responsibilities, and tools/technologies used. "
                + "Respond in a single formal English paragraph, no bullet points, strictly under 400 characters,There is no need to tell me the total number of characters. This is for the users to see, not for the background. "
                + "If experience information is missing, just respond with: \"insufficient data\". Do not add any suggestions or assumptions.";
    }

    public String getPersonalityprompt() {

        return "You are a professional resume analysis system. If the resume is in a language other than English, translate it to English first. " +
                "Then analyze and summarize the candidate’s personality traits based on their descriptions of projects, roles, and experience. " +
                "Focus on traits like leadership, creativity, problem-solving, collaboration, attention to detail, etc. " +
                "Write a single formal English paragraph, no bullet points, strictly under 300 characters,There is no need to tell me the total number of characters. This is for the users to see, not for the background. " +
                "Do not include any assumptions, suggestions, or system messages. If insufficient data is found, return: \"insufficient data\".";

    }

    public String getSoftSkillsprompt() {

        return "You are a professional resume analysis system. If the resume is not in English, translate it first. " +
                "Then identify and summarize the candidate’s language proficiencies and soft skills, such as communication, leadership, teamwork, adaptability, etc. " +
                "Write a single formal English paragraph, no bullet points, strictly under 400 characters. " +
                "Do not include explanations, assumptions, or system messages. " +
                "If such information is not found, reply with: \"insufficient data\".";
    }

    public String getPositionprompt() {

        return "You are a professional resume analysis system. If the resume is not in English, translate it first. " +
                "Then analyze the candidate’s work experience, skills, and projects. " +
                "Output a vertical list of exactly 5 suitable job positions, each with a brief name and a matching score (as a percentage). " +
                "Format each line like this: Job Title – Matching: 87%. " +
                "Only include relevant job roles. Do not give explanations, comments, or suggestions. No markdown or bullets. " +
                "If resume lacks sufficient data, just respond with: \"insufficient data\".";
    }

    public String getInformationprompt() {

        return "You are a professional resume analysis system. If the resume is in a language other than English, translate it first. " +
                "Then analyze the candidate’s personal background such as education, location, language proficiency, and cultural indicators. " +
                "Summarize which work environment, region, or industry would best suit them. Respond with a formal English paragraph under 400 characters. " +
                "Do not include suggestions, assumptions, or commentary. If data is insufficient, respond with: \"insufficient data\".";
    }

    public String getSalaryprompt() {

        return "You are a professional resume analysis system. Based on the candidate’s resume, estimate a suitable monthly salary range in Malaysian Ringgit (RM). " +
                "The output must be in the exact format: RMxxxx-xxxx (e.g., RM4000-7000). " +
                "The salary will be based on the working hours in Malaysia"+
                "Only include the salary range, no words, explanations, currency symbols, or extra text. " +
                "Always start with 'RM'. If data is insufficient, return: \"insufficient data\".";
    }

    public String getScoreprompt() {

        return "You are a professional resume evaluation system. Based on the resume content, assign an overall resume score out of 100. " +
                "The score should reflect work experience, project quality, skills, and relevance. " +
                "Respond strictly in the format: xxx/100 (e.g., 85/100). " +
                "Do not include any explanations or extra content. " +
                "If the resume lacks sufficient information, respond only with: \"insufficient data\".";
    }




}
