from bs4 import BeautifulSoup
import urllib3
import codecs
import re

url=input("Input URL: ")
filename=input("Input Series Name: ")
filename=filename+".txt"

http=urllib3.PoolManager()
response=http.request('GET',url)
soup=BeautifulSoup(response.data)

test = soup.findAll('table')[0]
l = []
for tr in test:
    tmpI=re.sub(r'(^[ \t]+|[ \t]+(?=:))', '', test.text, flags=re.M)
    l.append(tmpI)
f = codecs.open("Data/test", "w", encoding='utf8')
for s in l:
    f.write(s)
f.close()
f = codecs.open("Data/test", "r", encoding='utf8')
array = []
for line in f:
    if(line.isspace()):
        continue
    array.append(line)
f.close()
f = codecs.open("Data/"+filename, "w", encoding='utf8')
for s in range(22,len(array)):
    if 'View as Gallery' in array[s]:
            break
    if '[' in array[s]:
        continue
    if ']' in array[s]:
        continue
    if '/' in array[s]:
        continue
    print(array[s])
    f.write(array[s])
f.close()