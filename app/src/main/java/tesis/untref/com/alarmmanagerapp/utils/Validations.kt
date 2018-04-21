package tesis.untref.com.alarmmanagerapp.utils

fun checkThat(valid: Boolean, exception: Throwable) {
    if(!valid) throw exception
}