(ns blink-every-x.core
  (:use [toledohue.hue])
  (:use [clojure.pprint])
  (:gen-class))

(defn -main
  "blink lights every x minutes
  arg1 = desired amount of minutes"
  
  ;;ARITY 0
  ([]
   (println "\n\n\n\n\nYou Forgot The Argument.
Argument indicates the interval of time 
in minutes before the flashing\n\n\n\n\n")
   (hue 1 0 (get-user))
   (Thread/sleep 1000)
   (hue 1 12750 (get-user))
   (def user (get-user))

   (while true
     ;;if lights are already on just flash
     (cond (-> (get-light-info user)
               (:1)
               (:state)
               (:on))     (do (flash 1 user)
                              (Thread/sleep 1000)
                              (flash 1 user))

               ;;else turn on, flash and turn off
               :else      (do (turn-on 1 user) 
                              (Thread/sleep 1000)
                              (flash 1 user)
                              (Thread/sleep 1000)
                              (flash 1 user)
                              (turn-off 1 user)))
     ;;wait x minutes    
    (Thread/sleep (* 5000)))) 
  
  ;;ARITY 1
  ([x]
   (print "ARITY WORKING???")
   (if (not (number? x))
     (do (try
           (def a (num (read-string x)))
           (catch Exception e (do (println (str "caught exception: " (.getMessage e)))
                                  (def a 0)))))
     (def a x))
   (def user (get-user))

   (println (str "a is: " a " and its type is: " (type a)))
   (println (str "you selected flash every " a " minutes"))


   (while (and true (not= a 0))

     ;;if lights are already on just flash
     (cond (-> (get-light-info user)
               (:1)
               (:state)
               (:on))     (do (flash 1 user)
                              (Thread/sleep 1000)
                              (flash 1 user))

               ;;else turn on, flash and turn off
               :else      (do (turn-on 1 user) 
                              (Thread/sleep 1000)
                              (flash 1 user)
                              (Thread/sleep 1000)
                              (flash 1 user)
                              (turn-off 1 user)))
     ;;wait x minutes
     
     (Thread/sleep (* a 60000)))))





(defn fn4
  ([]
   (while true
     (println "1")
     (Thread/sleep 1000)))
  ([x]
   (while true
     (println x)
     (Thread/sleep 1000))))
