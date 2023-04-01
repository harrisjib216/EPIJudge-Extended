/**
 * Translate problem_mappings to more convenient format.
 *
 * Initially, problem_mapping is in format that is good for json updating.
 * However, it's not very easy to process data in this format.
 * This function translates the object in the following format:
 * {
 *   "Primitive Types": {
 *     "Count bits": {
 *       "C++: count_bits.cc": {
 *         "passed": 10000,
 *         "total": 10001
 *       },
 *       "Java: CountBits.java": {
 *         "passed": 0,
 *         "total": 10001
 *       },
 *       "Python: count_bits.py": {
 *         "passed": 0,
 *         "total": 10001
 *       }
 *     },
 *     ...
 *   },
 *   ...
 * }
 *
 * to
 *
 * [
 *   {
 *     "name": "Primitive Types",
 *     "progress": {
 *       "cpp": 0,
 *       "java": 7,
 *       "python": 13,
 *       "total": 13
 *     }
 *     "problems": [
 *       {
 *         "name": "Count bits",
 *         "cpp": {
 *           "filename": count_bits.cc",
 *           "passed": 10000,
 *           "total": 10001
 *         },
 *         "java": {
 *           "filename": CountBits.java",
 *           "passed": 10000,
 *           "total": 10001
 *         },
 *         "python": {
 *           "filename": count_bits.py",
 *           "passed": 10001,
 *           "total": 10001
 *         }
 *       },
 *       ...
 *     ]
 *   },
 *   ...
 * ]
 *
 * @param data - data from problem_mappings.json
 */
function translateProblemMappings(data) {
    function translateLanguageEntry(name, dirname, data) {
        let lang_field = Object.keys(data).find(x => x.startsWith(name));

        if (!lang_field) {
            console.log(`Can't find ${name} entry in ${data}`);
            return null
        }
        
        data = data[lang_field];
        data.filename = dirname + lang_field.split(" ")[1];
        
        return data
    }

    function translateProblem(name, data) {
        let result = {name};

        ALL_LANGUAGES.forEach((lang, index) => {
            result[lang] = translateLanguageEntry(
                PROPER_NAMES[index],
                `epi_judge_${lang}/`,
                data
            );
        });

        if (ALL_LANGUAGES.find((lang) => result[lang])) {
            return result;
        }

        return null;
    }

    function translateChapter(chapter_name, chapter_data) {
        let problems = [];
        
        let progress = {};

        for (const lang of ALL_LANGUAGES) {
            progress[lang] = 0;
        }

        for (const problem in chapter_data) {
            if (chapter_data.hasOwnProperty(problem) && chapter_data[problem]) {
                let result = translateProblem(problem, chapter_data[problem]);
                
                if (result) {
                    problems.push(result);

                    for (const lang of ALL_LANGUAGES) {
                        if (result[lang].passed === result[lang].total) {
                            progress[lang] += 1;
                        }
                    }
                }
            }
        }

        progress.total = problems.length;

        return {
            name: chapter_name,
            progress: progress,
            problems: problems
        }
    }

    let chapters = [];

    for (let chapter in data) {
        if (data.hasOwnProperty(chapter)) {
            chapters.push(translateChapter(chapter, data[chapter]))
        }
    }

    return chapters
}