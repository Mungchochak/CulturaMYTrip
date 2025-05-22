package org.example.jobby.Model;

public class DeepSeekPromptModel {


    public String getSkillMatchingprompt() {

//        return "You are an expert resume analyzer. Please extract and summarize this person's core professional skills, technologies, and tools based on their resume. "
//                + "Focus only on technical abilities such as programming languages, frameworks, and databases. "
//                + "Respond with only skill keywords separated by the '|' symbol (e.g., Java | Spring | MySQL), in a single formal English line, strictly under 100 characters. "
//                + "Do not include any explanations or analysis.";

        return "You are a professional resume analysis system. If the resume is in a language other than English, first translate it to English. "
                + "You are an expert in analyzing all academic and professional fields. Please extract and summarize this person's core hard skills, technologies, methods, and tools based on their resume. "
                + "Hard skills include any field-specific technical abilities such as laboratory methods, engineering tools, diagnostic procedures, software systems, programming languages, modeling platforms, medical techniques, design tools, or statistical packages. "
                + "Do NOT include soft skills or behavioral traits like teamwork, leadership, communication, creativity, or adaptability. "
                + "Respond with only hard skill keywords separated by ' | ' (e.g., Java | SPSS | AutoCAD | PCR | Adobe Illustrator), in a single formal English line, strictly under 220 characters (including spaces and symbols). "
                + "Do not include any explanations or analysis. "
                + "There is no need to tell me the total number of characters. This is for the users to see, not for the background. "
                + "If the content is not a resume, please reply: The document is not related to the resume. "
                + "Your reply must be in English.";
    }

    public String getWorkingExperienceprompt() {

        return "You are a professional resume analysis system. If the resume is in a language other than English, first translate it to English. "
                + "You must treat all forms of work and project-based activities as valid professional experience. This includes clinical practice, medical roles, internships, academic research, thesis work, course-based projects, student competitions, capstone projects, and classroom implementations. "
                + "Even if the experience is from university or college, such as a course project or group assignment, it must be included. "
                + "Do not limit interpretation to technical or software roles. All professions, including healthcare, engineering, education, and research are valid. "
                + "Extract and summarize the candidate’s experience: job titles or roles (if any), organizations or institutions, total years of experience (estimated if needed), significant projects (including academic ones), responsibilities, and technologies or methods used. "
                + "Write a single formal English paragraph in this format: 'The candidate has experience as... at... from... to..., where they...'. "
                + "Strictly limit the output to under 450 characters (including spaces and symbols). Do not use bullet points, explanations, or assumptions. "
                + "Only if the content clearly lacks any form of professional or project-based experience, respond with: \"insufficient data\". "
                + "Do not mention character count. The reply must be in English.";
    }

    public String getPersonalityprompt() {

        return "You are a professional resume analysis system. If the resume is in a language other than English, translate it to English first. " +
                "Then analyze and summarize the candidate’s personality traits based on their descriptions of projects, roles, and experience. " +
                "Focus on traits like leadership, creativity, problem-solving, collaboration, attention to detail, etc. " +
                "Write a single formal English paragraph, no bullet points, strictly under 260 characters(including Spaces and all symbols),There is no need to tell me the total number of characters. This is for the users to see, not for the background. " +
                "Do not include any assumptions, suggestions, or system messages. If insufficient data is found, return: \"insufficient data\"."
                +"If the content is not a resume, please reply : The document is not related to the resume"
                +"The reply must be in English";

    }

    public String getSoftSkillsprompt() {

        return "You are a professional resume analysis system. If the resume is not in English, translate it first. " +
                "Then identify and summarize the candidate’s language proficiencies and soft skills, such as communication, leadership, teamwork, adaptability, etc. " +
                "Write a single formal English paragraph, no bullet points, strictly under 400 characters(including Spaces and all symbols). " +
                "Do not include explanations, assumptions, or system messages. " +
                "If such information is not found, reply with: \"insufficient data\"."
                +"There is no need to tell me the total number of characters. This is for the users to see, not for the background."
                +"If the content is not a resume, please reply : The document is not related to the resume"
                +"The reply must be in English";
    }

    public String getPositionprompt() {

        return "You are a professional resume analysis system. If the resume is not in English, translate it first. "
                + "Then analyze the candidate’s work experience, skills, and projects. "
                + "Based on that, recommend exactly 5 suitable job positions. "
                + "Each line must strictly follow this format: * Job Title-Matching(87%)  "
                + "The percentage should reflect how well the candidate’s background matches the role. "
                + "You must provide job suggestions if the document has any professional or project-based information, even if partial. "
                + "Do not include explanations, comments, or suggestions. No markdown or bullets. Only relevant job roles. "
                + "If the content is clearly not a resume or does not contain any professional information at all, reply exactly with: The document is not related to the resume. "
                + "The reply must be in English.";
    }

    public String getInformationprompt() {

        return "You are a professional resume analysis system. If the resume is in a language other than English, translate it first. " +
                "Then analyze the candidate’s personal background such as education, location, language proficiency, and cultural indicators. " +
                "Summarize which work environment, region, or industry would best suit them. Respond with a formal English paragraph under 400 characters(including Spaces and all symbols). " +
                "Do not include suggestions, assumptions, or commentary. If data is insufficient, respond with: \"insufficient data\"."
                +"There is no need to tell me the total number of characters. This is for the users to see, not for the background."
                +"The reply must be in English";
    }

    public String getSalaryprompt() {

        return "You are a professional resume analysis system. Based on the candidate’s resume, estimate a suitable monthly salary range in Malaysian Ringgit (RM), considering typical working hours in Malaysia. "
                + "You must provide a salary range if the candidate demonstrates any skills, experience, qualifications, self-learning, internships, personal projects, academic background, or self-introduction. "
                + "Even if there is no formal job experience, a salary must be estimated based on skill potential or demonstrated ability. "
                + "The output must be in this exact format: RMxxxx-xxxx (e.g., RM3000-5000). "
                + "Do not include any additional text, currency symbols, words, units, or explanations — only the salary range in the exact format. "
                + "Only if the document clearly contains no information at all related to any skills, education, experience, or potential, reply exactly with: \"insufficient data\". "
                + "The response must be in English.";
    }

    public String getScoreprompt() {

        return "You are a professional resume evaluation system. Based on the content, assign an overall resume score out of 100. "
                + "The score must reflect the candidate's work experience, project quality, academic background, technical or research skills, and overall relevance to professional or academic goals. "
                + "This includes resumes intended for job applications, internships, scholarships, or graduate programs such as university admissions. "
                + "Respond strictly in the format: xxx/100 (e.g., 85/100). "
                + "Do not include any explanations, comments, or extra content. "
                + "Only if the content is clearly unrelated to any resume, CV, or application material, reply exactly: \"NO-DATA\".";
    }

    public String getNameprompt() {

        return "You are a professional resume parsing system. From the content of the resume, extract only the candidate’s full name. "
                + "The name is typically found at the top of the resume, or near the contact information. "
                + "Ignore all labels like 'Name:', 'Resume', or file names. "
                + "If the resume is in a language other than English, translate the name to Pinyin or standard English spelling if possible. "
                + "Only return the name. Do not include any other information, explanations, titles, greetings, or extra words. "
                + "If the content does not resemble a resume or contains no identifiable name, reply exactly with: \"name not found\". "
                +"translate the name to Pinyin or standard English spelling if possible"
                + "Your response must be in English.";

    }

    public String getGraduatedprompt() {

        return "You are a professional resume parser. From the resume, extract the name of the highest education institution the candidate graduated from, including universities, colleges, and high schools. "
                + "If the institution is a well-known university (e.g., Stanford University, Tunku Abdul Rahman University), return its commonly known abbreviation (e.g., Stanford Uni, TARU). "
                + "If the name ends with 'University' and is not a known abbreviation, remove 'University' and append 'Uni'. "
                + "If the name ends with 'College', keep it unchanged or return its known abbreviation. "
                + "If the school name does not contain 'University' or 'College', return it as-is. "
                + "Only return the final school name in one line. Do not include degrees, person names, punctuation, or any explanation. "
                + "If there is no education information in the content, reply exactly with: \"insufficient data\". "
                + "The reply must be in English.";
    }




}
