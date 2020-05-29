import psycopg2

conn = psycopg2.connect("dbname=ngex user=postgres password=postgres")
cur = conn.cursor()
cur.execute("SELECT * FROM ngex_class")
rows_c = cur.fetchall()

atributos = []

class Attributes:
   def __init__(self, attribute_name, field_name, attribute_type):
      self.attribute_name = attribute_name
      self.field_name = field_name
      self.attribute_type = attribute_type

class Classes:
   def __init__(self, class_name, package_name, entity_name):
      self.class_name = class_name
      self.package_name = package_name
      self.entity_name = entity_name

for r in rows_c:
   cl = Classes(r[1], r[2], r[3])

   cur.execute("select * from ngex_attribute where class_id = %s", [r[0]])
   rows_a = cur.fetchall()

   i = 1
   for r2 in rows_a:
      cr2 = Attributes( r2[2], r2[3], r2[5] )
      atributos.append(cr2)

import jinja2

from jinja2 import Template

f2 = open("template.txt")
htmlin = f2.read()

inPackage="br.com.empresa.softabc"
inClassName="Pessoa"

tpl = Template(htmlin)

modelClass = tpl.render(fields=atributos, classe=cl)

fw = open(inClassName+".java","w")
fw.write(modelClass)
fw.close()

## Generate Repository
fRepo = open("repository_template.txt")
fRepoIn = fRepo.read()

tpl = Template(fRepoIn)

repositoryClass = tpl.render(fields=atributos, classe=cl)

fw = open(inClassName+"Repository.java","w")
fw.write(repositoryClass)
fw.close()

## Generate Resource
fRes = open("resource_template.txt")
fResIn = fRes.read()

tpl = Template(fResIn)

resourceClass = tpl.render(fields=atributos, classe=cl)

fw = open(inClassName+"Resource.java","w")
fw.write(resourceClass)
fw.close()
