import pl.pak.rover.Coordinates
import pl.pak.rover.Rover
import pl.pak.rover.UnknownCommandException
import spock.lang.Specification

class CoordinatesAndDirectionTestSpock extends Specification{
    def "receiveStartingCoordinates" (){
        when:

        def results = new Rover(inputCoordinates).getCoordinates()
        then:
        def expected = receivedCoordinates

        results==expected

        where:
        inputCoordinates                             || receivedCoordinates
        new Coordinates(1.0, 1.0, 'N' as char)       || new Coordinates(1.0, 1.0, 'N' as char)
        new Coordinates(2.0, 1.0, 'N' as char)       || new Coordinates(2.0, 1.0, 'N' as char)
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
        var rover = new Rover(new Coordinates(0.0,0.0,direction as char))
        rover.receiveCommands('F' as char)

        when:
        def results = rover.getCoordinates()

        then:
        def expected = new Rover(new Coordinates(resultX as int, resultY as int, direction as char))
                .getCoordinates()

        results == expected

        where:
        direction || resultX || resultY
        'N'       || 0.0     || 1.0
        'S'       || 0.0     || -1.0
        'E'       || 1.0     || 0.0
        'W'       || -1.0    || 0.0
    }
    def "movesOneStepBackwardWhenReceivedBCommand" (){
        given:
        var rover = new Rover(new Coordinates(0.0,0.0,direction as char))
        rover.receiveCommands('B' as char)

        when:
        def results = rover.getCoordinates()

        then:
        def expected = new Rover(new Coordinates(resultX as int, resultY as int, direction as char))
                .getCoordinates()

        results == expected

        where:
        direction || resultX || resultY
        'N'       || 0.0     || -1.0
        'S'       || 0.0     || 1.0
        'E'       || -1.0    || 0.0
        'W'       || 1.0     || 0.0
    }
}
