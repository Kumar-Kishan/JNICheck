#include "Hello.h"
#include<string.h>
#include<iostream>


double fRand(double fMin, double fMax)
{
    double f = (double)rand() / RAND_MAX;
    return fMin + f * (fMax - fMin);
}

JNIEXPORT jobjectArray JNICALL Java_Hello_getRandomLocations(JNIEnv *env, jobject, jint number)
{
    jclass locationClass = env->FindClass("LLocation;");
    jmethodID miDLocationConstructor = env->GetMethodID(locationClass, "<init>", "(DD)V");
    jobjectArray locations = env->NewObjectArray(number, locationClass, 0);
    for(int i=0; i< number; i++)
        env->SetObjectArrayElement(locations, i ,env->NewObject(locationClass, miDLocationConstructor, fRand(0,100), fRand(100,200)));
    
    //jobject locationObject;
    return locations;
}




