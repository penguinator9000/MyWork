import sys, numpy as np

class Library():
  def __init__(self, index, bookNum, signupDays, booksPerDay, books):
    self.index = index
    self.bookNum = bookNum
    self.signupDays = signupDays+1
    self.booksPerDay = booksPerDay
    self.bookScore = [bList[i] for i in books]
    self.books = books
    self.scannedBooks = set()

    self.eff = self.calcEff()

  def calcEff(self):
    bAvg = sum(self.bookScore)/len(self.bookScore)
    mad = np.mean(self.bookScore)
    eff = ((bAvg/mad)*self.booksPerDay)/self.signupDays
    return eff
  
  def getBook(self):
    if self.books:
      x = self.books.pop(0)
      if bookDic[x[0]][1] == 1:
        return self.getBook()
      return x
    return None

  def buildBooks(self):
    self.books = sorted([(i, bList[i]) for i in self.books], key=lambda x: x[1], reverse=True)

f = open("c_incunabula.txt", "r")
b, l, d = map(int, f.readline().split())
bList = list(map(int, f.readline().split()))

libs = list()

for i in range(l):
  a, b1, c = map(int, f.readline().split())
  l = list(map(int, f.readline().split()))
  libs.append(Library(i,a,b1,c,l))

bookDic = {}
for i in range(b):
  bookDic[i] = [bList[i], 0]

libs.sort(key=lambda x: x.eff, reverse=True)

signedUp = list()

for i in range(d):
  if libs:
    libs[0].signupDays -= 1
    if libs[0].signupDays == 0:
      libs[0].buildBooks()
      signedUp.append(libs.pop(0))

  for j in signedUp:
    for k in range(j.booksPerDay):
      book = j.getBook()
      if book:
        bookDic[book[0]][1] = 1
        j.scannedBooks.add(str(book[0]))

f.close()
with open("ans2.txt", "w") as f:
  f.write(str(len(signedUp))+"\n")
  for i in signedUp:
    f.write(str(i.index)+" "+str(len(i.scannedBooks))+"\n")
    f.write(" ".join(i.scannedBooks)+"\n")
