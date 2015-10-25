

String comdata = "";
int lightPin=13;

void setup()
{
    Serial.begin(9600);
    pinMode(13, OUTPUT);
    pinMode(12, OUTPUT);
}

void loop()
{
    while (Serial.available() > 0)  
    {
        comdata += char(Serial.read());
        delay(2);
    }
    if (comdata.length() > 0)
    {
        
        Serial.println(comdata);
        if(comdata=="a")
        {
          light(50,3);
        }
        else
        {
          light(80,0.1);
        }
        comdata = "";
    }
}
void light(float x,float y)
{
  for(int i=0;i<(5/y);i++)
  {
    digitalWrite(13,HIGH);
    digitalWrite(12,HIGH);
    float z=x/100*y;
    delay(z*1000);
    digitalWrite(13,LOW);
    digitalWrite(12,LOW);
    z=(100-x)/100*y;
    delay(z*1000);
    
  }
}

