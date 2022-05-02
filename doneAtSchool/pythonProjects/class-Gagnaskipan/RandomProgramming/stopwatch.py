import time as fuckoff
class Stopwatch():
    def __init__(self, inp = 0):
        self.time = inp
    def start(self):
        self.start = fuckoff.time_ns()

    def stop(self):
        self.stop = fuckoff.time_ns()
        self.time = self.stop - self.start

    def __add__(self, other):
        return Stopwatch(self.time + other.time)

    def __sub__(self, other):
        return Stopwatch(self.time - other.time)


time = Stopwatch()
time.start()
time.stop()
print(time.time)