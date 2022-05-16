import pl.pak.rover.Coordinates
import pl.pak.rover.Direction
import pl.pak.rover.Rover
import pl.pak.rover.UnknownCommandException
import spock.lang.Specification

import static pl.pak.rover.Direction.East
import static pl.pak.rover.Direction.North
import static pl.pak.rover.Direction.South
import static pl.pak.rover.Direction.West

class CoordinatesAndDirectionTestSpock extends Specification{
    def "receiveStartingCoordinates" (){

        expect:
        expectedCoordinates==new Rover(inputCoordinates).getCoordinates()

        where:
        inputCoordinates                             || expectedCoordinates
        new Coordinates(1.0, 1.0, North)             || new Coordinates(1.0, 1.0, North)
        new Coordinates(2.0, 1.0, North)       || new Coordinates(2.0, 1.0, North)
    }

    def "throwsAnExceptionWhenReceivesUnknownCommand"(){
        when:
        def results = new Rover().receiveCommands('u' as char)

        then:

       UnknownCommandException un = thrown()
        un.message == 'Unknown command: u'

    }

    def "movesOneStepForwardWhenReceivedFCommand" (){
        given:
        var rover = new Rover(new Coordinates(0.0,0.0,direction))


        when:
        rover.receiveCommands('F' as char)
        def results = rover.getCoordinates()

        then:
        def expected = new Coordinates(resultX, resultY, direction)


        results == expected

        where:
        direction || resultX || resultY
        North       || 0.0     || 1.0
        South       || 0.0     || -1.0
        East       || 1.0     || 0.0
        West       || -1.0    || 0.0
    }
    def "movesOneStepBackwardWhenReceivedBCommand" (){
        given:
        var rover = new Rover(new Coordinates(0.0,0.0,direction))


        when:
        def results = rover.getCoordinates()
        rover.receiveCommands('B' as char)
        then:
        def expected = new Coordinates(resultX, resultY, direction)


        results == expected

        where:
        direction || resultX || resultY
        North       || 0.0     || -1.0
        South       || 0.0     || 1.0
        East       || -1.0    || 0.0
        West       || 1.0     || 0.0
    }
}
