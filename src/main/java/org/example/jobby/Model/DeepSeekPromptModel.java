package org.example.jobby.Model;

public class DeepSeekPromptModel {


    public String getSkillMatchingprompt() {


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

//        return "You are a professional resume analysis system. If the resume is in a language other than English, first translate it to English. "
//                + "You must treat all forms of work and project-based activities as valid professional experience. This includes clinical practice, medical roles, internships, academic research, thesis work, course-based projects, student competitions, capstone projects, and classroom implementations. "
//                + "Even if the experience is from university or college, such as a course project or group assignment, it must be included. "
//                + "Do not limit interpretation to technical or software roles. All professions, including healthcare, engineering, education, and research are valid. "
//                + "Extract and summarize the candidate’s experience: job titles or roles (if any), organizations or institutions, total years of experience (estimated if needed), significant projects (including academic ones), responsibilities, and technologies or methods used. "
//                + "Write a single formal English paragraph in this format: 'The candidate has experience as... at... from... to..., where they...'. "
//                + "Strictly limit the output to under 450 characters (including spaces and symbols). Do not use bullet points, explanations, or assumptions. "
//                + "Only if the content clearly lacks any form of professional or project-based experience, respond with: \"insufficient data\". "
//                + "Do not mention character count. The reply must be in English.";
//        return "Summarize up to 3 key professional or academic experiences from the resume in concise format. "
//                + "Include formal work, internships, course projects, academic assignments, team competitions, and personal projects. "
//                + "Each line must follow this format:\n"
//                + "[Role or Project] – [Company, School, or Event] (Year) – [Main task or result, max 10 words]\n\n"
//                + "Be minimal and factual. Avoid full sentences, adjectives, and unnecessary phrases. Limit each line to under 100 characters. Most recent first. "
//                + "If no valid experience is found, reply exactly: insufficient data.";


        return "You are a professional resume analysis system. Extract the candidate’s major **project or responsibility-based experiences**, including:\n"
                + "- Personal projects\n"
                + "- Group or coursework projects\n"
                + "- Group projects or capstone projects\n"
                + "- Internship-based or job-based projects\n"
                + "- Significant responsibilities or initiatives in formal employment\n\n"
                + "Use this structured format exactly for each experience:\n"
                + "Category: [e.g., Work Project, Internship Project, Coursework Project]\n"
                + "→ Role: [e.g., Developer, Lead, Researcher, Coordinator]\n"
                + "→ Organization: [Company, School, or Platform involved]\n"
                + "→ Year: [e.g., 2023 or 2020.06 - 2021.09]\n"
                + "→ Description: [Key tasks, technologies, objectives, and impact]\n\n"
                + "Each entry must follow this format and be separated by a blank line.\n"
                + "If no relevant content is found, return: \"insufficient data\".";

    }

    public String getPersonalityprompt() {

//        return "You are a professional resume analysis system. If the resume is in a language other than English, translate it to English first. " +
//                "Then analyze and summarize the candidate’s personality traits based on their descriptions of projects, roles, and experience. " +
//                "Focus on traits like leadership, creativity, problem-solving, collaboration, attention to detail, etc. " +
//                "Write a single formal English paragraph, no bullet points, strictly under 260 characters(including Spaces and all symbols),There is no need to tell me the total number of characters. This is for the users to see, not for the background. " +
//                "Do not include any assumptions, suggestions, or system messages. If insufficient data is found, return: \"insufficient data\"."
//                +"If the content is not a resume, please reply : The document is not related to the resume"
//                +"The reply must be in English";


            return "You are a professional resume analysis system. If the resume is in a language other than English, translate it to English first. "
                    + "Analyze and summarize the candidate’s personality traits based on descriptions of their roles, responsibilities, and projects. "
                    + "Focus on traits such as leadership, creativity, problem-solving, collaboration, attention to detail, etc. "
                    + "Respond with a single formal English paragraph under 260 characters (including spaces and symbols). "
                    + "Do not include assumptions or system comments. "
                    + "If insufficient data is found, return: insufficient data. "
                    + "If the document is not a resume, reply: The document is not related to the resume.";


    }

    public String getSoftSkillsprompt() {

        return "You are a professional resume analysis system. If the resume is not in English, translate it first. " +
                "Then extract:\n" +
                "1. All language proficiencies mentioned in the resume.\n" +
                "2. All soft skills such as communication, leadership, teamwork, adaptability, etc.\n\n" +
                "Respond in the following format (only this format):\n\n" +
                "Language: English | Mandarin | Spanish\n" +
                "Soft Skills: Leadership | Problem-solving | Teamwork\n\n" +
                "Do NOT include any explanations, levels (e.g. fluent), or commentary. Return 'insufficient data' if nothing is found in each category respectively.\n" +
                "Use only English in your response.";
    }

    public String getPositionprompt() {

        return "You are a professional resume analysis system. Translate the resume to English if necessary, and recommend job positions based on the candidate’s experience, skills, or project background.\n\n"
                + "Group your suggestions using a clear category label in this format:\n"
                + "Category: [e.g., Software Engineering, UX Design, Data Analysis, etc.]\n"
                + "→ Position: [Job Title, e.g., Front-End Developer]\n"
                + "→ Match: [Matching percentage, e.g., 87%]\n"
                + "→ Description: [One or two lines summarizing why the candidate is suitable]\n\n"
                + "Each recommended role must be backed by relevant resume content (experience, skills, or projects).\n"
                + "Return exactly 5 positions across 1–3 categories. Use professional judgment to group roles.\n"
                + "Use English only. Do not use markdown or bullets. Keep the output clean and structured.\n"
                + "If no work-related or project-based content is found, respond with: The document is not related to the resume.";
    }

    public String getInformationprompt() {


        return "You are a professional resume analysis system. Extract the candidate’s basic personal and academic background using the format below:\n\n"
                + "Name: [Full name in English or Pinyin]\n"
                + "Location: [City or region]\n"
                + "Major: [University major, translated if needed]\n"
                + "Certificates:\n"
                + "- [Concise Certificate Name 1]: [A clear and specific explanation of what the certificate represents, including the issuing body, proficiency level, and purpose]\n"
                + "- [Concise Certificate Name 2]: [Detailed explanation as above]\n\n"
                + "Each field must start on a new line. Certificate names must be short and human-readable (e.g., 'CET4', 'HSK 5'), followed by a colon and a complete English explanation.\n"
                + "Do not include generic phrases like 'a certificate' or 'proves proficiency' without specifying in what and how.\n"
                + "If any section is not available, omit it. Do not include any markdown, bullet formatting, or commentary.\n"
                + "Your response must be in clean, well-formatted English only.";

    }



    public String getSalaryprompt() {

        return "You are a professional resume analysis system. Based on the candidate’s resume, estimate a suitable monthly salary range in Malaysian Ringgit (RM). "
                + "You must always provide both:\n"
                + "1. Recommended Salary: RMxxxx–xxxx (e.g., RM3000–5000)\n"
                + "2. Explanation: A short sentence justifying the estimate (e.g., based on skills, tools, or project experience)\n\n"
                + "Example output:\n"
                + "Recommended Salary: RM3000–5000\n"
                + "Explanation: Based on UI/UX design experience, software proficiency, and strong project outcomes.\n\n"
                + "If there is absolutely no relevant data (skills, education, projects, internships), reply exactly with: \"insufficient data\".\n"
                + "The response must be in English, clean, and without any extra comments.";
    }

    public String getScoreprompt() {

        return "You are a professional resume evaluation system. Based on the provided resume content, assign an overall resume score out of 100. "
                + "The score must reflect the candidate's work experience, project quality, academic background, technical or research skills, and relevance to job or academic goals. "
                + "Strictly respond in this format:\n\n"
                + "90/100\n\n"
                + "Explanation:\n"
                + "1. [Evaluation of work experience, e.g., role diversity, seniority, relevance]\n\n"
                + "2. [Assessment of project quality, scope, technologies used, and outcomes]\n\n"
                + "3. [Academic background strength, institution ranking, GPA, relevance]\n\n"
                + "4. [Technical or research skills: tools, languages, publications, etc.]\n\n"
                + "5. [Overall structure, clarity, formatting, and impact of the resume]\n\n"
                + "Each explanation point must be concise, labeled with a number, and separated by a blank line.\n"
                + "If the content is unrelated to resumes or applications, reply exactly with: NO-DATA.\n"
                + "Your response must be in English.";
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

        return "You are a professional resume analysis system. From the resume, extract the name of the highest-level educational institution attended (e.g., university, college, or high school).\n"
                + "Return only the institution name in this format:\n"
                + "- If the name ends with 'University', remove it and add 'Uni'. (e.g., Stanford University → Stanford Uni)\n"
                + "- If it ends with 'College', leave it unchanged or use its abbreviation.\n"
                + "- If it's neither, return the name directly.\n\n"
                + "Then provide detailed explanation in this format, one line per item:\n"
                + "Explanation:\n"
                + "1. School background and academic strengths\n"
                + "2. World ranking (QS, THE, etc.)\n"
                + "3. Ranking for the candidate's field of study\n"
                + "4. Notable alumni or public figures\n\n"
                + "If no education-related information is found, return exactly: \"insufficient data\".\n"
                + "Respond in English. Do not use markdown or additional formatting.";
    }

    public String getCgpaprompt() {
        return "You are a professional resume parser. From the resume, extract the candidate’s CGPA (Cumulative Grade Point Average) or GPA if available.\n\n"
                + "Return the GPA strictly in the format:\n"
                + "x.x/4.0 or xx/4.0 (e.g., 3.4/4.0 or 3.75/4.0)\n\n"
                + "If multiple GPAs are listed, return the highest.\n"
                + "Do not include any explanation, prefix, label, or extra text — only return the GPA value.\n"
                + "If there is no GPA or CGPA mentioned at all, reply exactly with: \"insufficient data\".\n"
                + "Respond only in English.";
    }






}
