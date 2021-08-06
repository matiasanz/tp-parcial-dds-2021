let timeout=null

function esperarYEnviar(segundos, input){
    let enviar = function (){
        input.form.submit()
    }

    clearTimeout(timeout)
    timeout = setTimeout(enviar, segundos*1000)
}