import os
import time 

folders = []
subDirectories = ["Assignments", "Notes", "Finals"]
def newSemester(pathExtention="", localPath="", customFormat = False):
    if customFormat:
        global subDirectories
        string = input('Enter custom format fx: dir1;dir2: ')
        if string:
            subDirectories = string.split(";")
    if not pathExtention:
        season = "Fall"
        if int(time.gmtime().tm_mon) <= 7:
            season = "Spring"
        pathExtention = "s" + str(time.gmtime().tm_year) + season
    if localPath:
        path = localPath + "\\" + pathExtention
    else:
        path = os.getcwd() + "\\" + pathExtention
    try:
        os.mkdir(path)
    except OSError:
        print ("Creation of the directory %s failed" % path)
    else:
        print ("Successfully created the directory %s " % path)
        createCourseDirectories(path)
        addFolder(pathExtention)

def createCourseDirectories(path):
    success = False
    numCourses = input("Enter number of courses: ")
    while not success:
        try:
            numCourses = int(numCourses)
        except ValueError:
            print("You have to enter only a number.")
            numCourses = input("Please re-enter number of courses: ")
        else:
            success = True
    
    for i in range(numCourses):
        createCourse(input("Enter a name for course #{}: ".format(i+1)), path)

def createCourse(courseName, localPath):
    success = False
    courseName = courseName.lower()
    path = localPath + "\\" + courseName
    while not success:
        courseName = courseName.lower()
        try:
            os.mkdir(path)
        except OSError:
            print ("The directory %s already exists." % courseName)
            path = localPath + "\\" + input("Please re-enter course name: ")
        else:
            success = True
            addFolder(courseName)
            createSubDirectories(path)

def createSubDirectories(localPath):
    #if subDirectories == []:
    #    subDirectories = ["Assignments", "Notes", "Finals"]
    for sub in subDirectories:
        os.mkdir(localPath + "\\" + sub)
        addFolder(sub)

def addFolder(folderName):
    folders.append(folderName)


season = "Fall"
if int(time.gmtime().tm_mon) <= 7:
    season = "Spring"
if input("Do you have a custom format (y/n)? ") == "y":
    newSemester(pathExtention=input("Enter directry name (if blanc '{}'): ".format("s" + str(time.gmtime().tm_year) + season)), customFormat=True)
else:
    newSemester()#input("Enter test: "))
print("Done, {} directories created.".format(len(folders)))

"""
semester
    / course 1
        / Assignments
        / Notes
        / Finals
"""
